package org.demo.mavenbuildanalyzer;

import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.parser.MavenLogParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        MavenLogParser parser = new MavenLogParser();

        String log = Files.readString(Path.of("src/samples/maven-failed-dependency-resolution.log"));

        BuildAnalysis analysis = parser.parse(log);

        System.out.println(analysis.getBuildStatus());
        System.out.println(analysis.getBuildTime());
        System.out.println(analysis.getFinishedAt());
        System.out.println(analysis.getFailureType());
        System.out.println();
    }
}
