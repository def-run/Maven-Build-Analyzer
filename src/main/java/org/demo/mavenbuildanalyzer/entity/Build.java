package org.demo.mavenbuildanalyzer.entity;

import jakarta.persistence.*;
import org.demo.mavenbuildanalyzer.model.FailureType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "builds")
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buildStatus;
    private String buildTime;
    private String finishedAt;

    @Enumerated(EnumType.STRING)
    private FailureType failureType;

    private String plugin;

    @OneToMany(
            mappedBy = "build",
            cascade = CascadeType.ALL
    )
    private List<BuildException> exceptions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public FailureType getFailureType() {
        return failureType;
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

    public List<BuildException> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<BuildException> exceptions) {
        this.exceptions = exceptions;
    }
}
