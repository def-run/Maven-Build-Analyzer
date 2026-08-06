package org.demo.mavenbuildanalyzer.parser;

import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.model.ExceptionInfo;

import java.util.ArrayList;
import java.util.List;
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

    private void extractRootCause() {
        
    }
}
