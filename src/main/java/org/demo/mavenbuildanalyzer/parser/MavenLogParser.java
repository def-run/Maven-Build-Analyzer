package org.demo.mavenbuildanalyzer.parser;

import org.demo.mavenbuildanalyzer.classifier.FailureClassifier;
import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.model.ExceptionInfo;
import org.demo.mavenbuildanalyzer.model.FailureType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MavenLogParser {

    private static final Pattern EXCEPTION_PATTERN =
            Pattern.compile("([a-zA-Z_$][\\w$]*\\.)+[A-Z][\\w$]*(Exception|Error)");

    public BuildAnalysis parse(String logs) {
        String[] lines = logs.split("\\R");

        BuildAnalysis analysis = new BuildAnalysis();

        analysis.setBuildStatus(extractBuildStatus(lines));
        analysis.setExceptions(extractExceptions(lines));
        analysis.setBuildTime(extractBuildTime(lines));
        analysis.setFinishedAt(extractFinishedAt(lines));

        if("STATUS: SUCCESS".equals(analysis.getBuildStatus())) {
            analysis.setFailureType(FailureType.NO_FAILURE);
        } else {
            analysis.setFailureType(extractFailureType(lines));
        }

        return analysis;
    }

    private String extractBuildStatus(String[] lines) {
        for(String line : lines) {
            line = line.trim();

            if(line.equals("[INFO] BUILD SUCCESS")) {
                return "SUCCESS";
            }
            if(line.equals("[ERROR] BUILD FAILURE")) {
                return "FAILURE";
            }
        }

        return "UNKNOWN";
    }

    private String extractBuildTime(String[] lines) {
        for(String line : lines) {
            line = line.trim();

            if(line.startsWith("[INFO] Total time: ")) {
                int index = line.indexOf(":");

                return line.substring((index + 1)).trim();
            }
        }

        return "UNKNOWN";
    }

    private String extractFinishedAt(String[] lines) {
        for(String line : lines) {
            line = line.trim();

            if(line.startsWith("[INFO] Finished at: ")) {
                int index = line.indexOf(":");

                return line.substring((index + 1)).trim();
            }
        }

        return "UNKNOWN";
    }

    private List<ExceptionInfo> extractExceptions(String[] lines) {
        List<ExceptionInfo> info = new ArrayList<>();


        for(String line : lines) {
            line = line.trim();
            Matcher matcher = EXCEPTION_PATTERN.matcher(line);

            if(matcher.find()) {
                ExceptionInfo exceptionInfo = new ExceptionInfo();

                exceptionInfo.setName(matcher.group());

                String message = line.substring(matcher.end()).trim();

                if(message.startsWith(":")) {
                    message = message.substring(1).trim();
                }

                exceptionInfo.setMessage(message);
                info.add(exceptionInfo);
            }
        }
        return info;
    }

    private FailureType extractFailureType(String[] lines) {
        FailureClassifier classifier = new FailureClassifier();

        return classifier.classify(lines);
    }
}
