package org.demo.mavenbuildanalyzer.model;

import java.util.List;

public class BuildAnalysis {

    private String buildStatus;
    private String buildTime;
    private String finishedAt;
    private FailureType failureType;
    private String plugin;
    private List<ExceptionInfo> exceptions;

    public BuildAnalysis() {}

    public String getBuildStatus() {
        return "STATUS: " + buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    public String getBuildTime() {
        return "BUILD TIME: " + buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getFinishedAt() {
        return "FINISHED AT: " + finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getFailureType() {
        return "FAILURE TYPE: " + failureType;
    }

    public void setFailureType(FailureType failureType) {
        this.failureType = failureType;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public List<ExceptionInfo> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ExceptionInfo> exceptions) {
        this.exceptions = exceptions;
    }
}
