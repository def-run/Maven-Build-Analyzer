package org.demo.mavenbuildanalyzer.service;

import org.demo.mavenbuildanalyzer.entity.Build;
import org.demo.mavenbuildanalyzer.entity.BuildException;
import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.model.ExceptionInfo;
import org.demo.mavenbuildanalyzer.parser.MavenLogParser;
import org.demo.mavenbuildanalyzer.repository.BuildRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildAnalysisService {

    private final MavenLogParser parser;
    private final BuildRepository buildRepository;

    public BuildAnalysisService(MavenLogParser parser, BuildRepository buildRepository) {
        this.parser = parser;
        this.buildRepository = buildRepository;
    }

    public BuildAnalysis analyze(String logContent) {
        BuildAnalysis analysis = parser.parse(logContent);

        Build build = new Build();

        build.setBuildStatus(analysis.getBuildStatus());
        build.setBuildTime(analysis.getBuildTime());
        build.setFinishedAt(analysis.getFinishedAt());
        build.setFailureType(analysis.getFailureType());
        build.setPlugin(analysis.getPlugin());

        for(ExceptionInfo e : analysis.getExceptions()) {
            BuildException exception = new BuildException();

            exception.setName(e.getName());
            exception.setMessage(e.getMessage());

            exception.setBuild(build);
            build.getExceptions().add(exception);
        }

        buildRepository.save(build);

        return analysis;
    }
}
