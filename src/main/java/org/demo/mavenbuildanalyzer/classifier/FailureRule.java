package org.demo.mavenbuildanalyzer.classifier;

import org.demo.mavenbuildanalyzer.model.FailureType;

import java.util.List;

public class FailureRule {
    private FailureType failureType;
    private List<String> keywords;

    public FailureRule(FailureType failureType, List<String> keywords) {
        this.failureType = failureType;
        this.keywords = keywords;
    }

    public boolean matches(String line) {
        line = line.trim().toLowerCase();

        for(String keyword : keywords) {
            if(line.contains(keyword.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
