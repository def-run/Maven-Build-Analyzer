package org.demo.mavenbuildanalyzer.parser;

import org.demo.mavenbuildanalyzer.classifier.FailureClassifier;
import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.model.ExceptionInfo;
import org.demo.mavenbuildanalyzer.model.FailureType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MavenLogParser {

    private static final Pattern EXCEPTION_PATTERN =
            Pattern.compile("([a-zA-Z_$][\\w$]*\\.)+[A-Z][\\w$]*(Exception|Error)");

    private static final Pattern PLUGIN_PATTERN =
            Pattern.compile("^\\[INFO\\] --- ([\\w.-]+):([\\w.-]+):([\\w.-]+) \\(([^)]+)\\) @ ([\\w.-]+) ---$");

    private static final Pattern CURRENT_MODULE =
            Pattern.compile("^\\[INFO\\].*<\\s*([^:]+):([^>]+)\\s*>.*$");

    public BuildAnalysis parse(String logs) {
        String[] lines = logs.split("\\R");

        BuildAnalysis analysis = new BuildAnalysis();

        analysis.setBuildStatus(extractBuildStatus(lines));
        analysis.setExceptions(extractExceptions(lines));
        analysis.setBuildTime(extractBuildTime(lines));
        analysis.setFinishedAt(extractFinishedAt(lines));

        if("SUCCESS".equals(analysis.getBuildStatus())) {
            analysis.setFailureType(FailureType.NO_FAILURE);
        } else {
            analysis.setFailureType(extractFailureType(lines));
        }

        analysis.setPlugin(extractPlugin(lines));

        return analysis;
    }

    private String extractBuildStatus(String[] lines) {
        for(String line : lines) {
            line = line.trim();

            if(line.contains("[INFO] BUILD SUCCESS")) {
                return "SUCCESS";
            }
            if(line.contains("[ERROR] BUILD FAILURE") || line.contains("[INFO] BUILD FAILURE")) {
                return "FAILURE";
            }
        }

        return "UNKNOWN";
    }

    private String extractBuildTime(String[] lines) {
        for(String line : lines) {
            line = line.trim();

            if(line.contains("Total time: ")) {
                int index = line.indexOf(":");

                return line.substring((index + 1)).trim();
            }
        }

        return "UNKNOWN";
    }

    private String extractFinishedAt(String[] lines) {
        for(String line : lines) {
            line = line.trim();

            if(line.contains("[INFO] Finished at: ")) {
                int index = line.indexOf(":");

                return line.substring((index + 1)).trim();
            }
        }

        return "UNKNOWN";
    }

    private List<ExceptionInfo> extractExceptions(String[] lines) {
        List<ExceptionInfo> info = new ArrayList<>();


        for(int i = 0; i < lines.length; i++) {
            String line = lines[i];
            line = line.trim();
            Matcher matcherException = EXCEPTION_PATTERN.matcher(line);

            if(matcherException.find()) {
                ExceptionInfo exceptionInfo = new ExceptionInfo();

                exceptionInfo.setName(matcherException.group());

                String message = line.substring(matcherException.end()).trim();

                if(message.startsWith(":")) {
                    message = message.substring(1).trim();
                }
                if(message.isEmpty()) {
                    message = "";
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

    private String extractPlugin(String[] lines) {
        String plugin = null;
        for(String line : lines) {
            line = line.trim();
            Matcher matcherPlugin = PLUGIN_PATTERN.matcher(line);
            Matcher matcherModule = CURRENT_MODULE.matcher(line);

            if(matcherModule.find()) {
                plugin = null;
            }

            if(matcherPlugin.find()) {
                plugin = matcherPlugin.group(1);
            }

            if(line.contains("[ERROR] BUILD FAILURE") || line.contains("[INFO] BUILD FAILURE")) {
                return plugin != null ? plugin : "UNKNOWN";
            }
        }

        return "UNKNOWN";
    }
}
