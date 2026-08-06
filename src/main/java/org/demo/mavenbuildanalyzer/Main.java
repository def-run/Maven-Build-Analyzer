package org.demo.mavenbuildanalyzer;

import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.parser.MavenLogParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        MavenLogParser parser = new MavenLogParser();

        String log = Files.readString(Path.of("src/samples/maven-exception.log"));

        BuildAnalysis analysis = parser.parse(log);

        System.out.println(analysis.getBuildStatus());
        System.out.println();
        System.out.println("EXCEPTIONS: ");
        for(Map.Entry<String, String> e : analysis.getExceptionName().entrySet()) {
            System.out.println(e);
        }
    }
}
