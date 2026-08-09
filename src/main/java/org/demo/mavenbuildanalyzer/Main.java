package org.demo.mavenbuildanalyzer;

import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.model.ExceptionInfo;
import org.demo.mavenbuildanalyzer.parser.MavenLogParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        MavenLogParser parser = new MavenLogParser();

        String log = Files.readString(Path.of("src/samples/maven-exception.log"));

        BuildAnalysis analysis = parser.parse(log);

        System.out.println("Build Status: " + analysis.getBuildStatus());
        System.out.println("Build Time: " + analysis.getBuildTime());
        System.out.println("Finished At: " + analysis.getFinishedAt());
        System.out.println("Failure Type: " + analysis.getFailureType());
        System.out.println("Plugin executing when build failed: " + analysis.getPlugin());
        System.out.println("Exceptions: ");
        for(ExceptionInfo exceptions : analysis.getExceptions()) {
            System.out.println(exceptions);
        }
    }
}
