package org.demo.mavenbuildanalyzer.model;

public enum FailureType {
    COMPILATION,
    DEPENDENCY_RESOLUTION,
    TEST_EXECUTION,
    PLUGIN_EXECUTION,
    APPLICATION_STARTUP,
    PACKAGING,
    DEPLOYMENT,
    NO_FAILURE,
    UNKNOWN
}
