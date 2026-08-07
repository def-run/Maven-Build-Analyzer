package org.demo.mavenbuildanalyzer.classifier;

import org.demo.mavenbuildanalyzer.model.FailureType;

import java.util.List;

public class FailureClassifier {
    private final List<FailureRule> rules = List.of(
            new FailureRule(
                    FailureType.COMPILATION,
                    List.of(
                            "COMPILATION ERROR",
                            "CompilationFailureException",
                            "Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin",

                            "cannot find symbol",
                            "cannot access",
                            "package ... does not exist",

                            "incompatible types",
                            "inconvertible types",
                            "bad operand types",

                            "illegal start of expression",
                            "';' expected",
                            "')' expected",
                            "'(' expected",
                            "'}' expected",
                            "reached end of file while parsing",

                            "class, interface, enum, or record expected",
                            "duplicate class",
                            "invalid method declaration",
                            "invalid constructor declaration",

                            "cannot be applied to given types",
                            "is ambiguous",

                            "might not have been initialized",
                            "is already defined",

                            "does not override or implement a method from a supertype",

                            "release version",
                            "source release",
                            "target release",
                            "invalid source release",
                            "invalid target release",

                            "cannot infer type arguments",
                            "unchecked conversion",
                            "unchecked assignment"
                    )
            ),

            new FailureRule(
                    FailureType.DEPENDENCY_RESOLUTION,
                    List.of(
                            "DependencyResolutionException",
                            "ArtifactResolutionException",
                            "ArtifactNotFoundException",
                            "Failed to collect dependencies",
                            "Could not resolve dependencies",
                            "Could not find artifact",
                            "Failed to read artifact descriptor",
                            "Could not transfer artifact",
                            "TransferFailedException",
                            "Non-resolvable parent POM",
                            "UnresolvableModelException",
                            "Checksum validation failed",
                            "UnknownHostException",
                            "Connection timed out",
                            "Connection refused",
                            "Read timed out",
                            "PKIX path building failed",
                            "SSLHandshakeException",
                            "Received fatal alert",
                            "Repository does not exist",
                            "Could not download",
                            "Failure to find",
                            "401 Unauthorized",
                            "403 Forbidden",
                            "407 Proxy Authentication Required",
                            "NoRepositoryConnectorException",
                            "Failed to execute goal org.apache.maven.plugins:maven-dependency-plugin"
                    )
            ),

            new FailureRule(
                    FailureType.TEST_EXECUTION,
                    List.of(
                            "Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin",
                            "Failed to execute goal org.apache.maven.plugins:maven-failsafe-plugin",
                            "There are test failures",
                            "Tests run:",
                            "Failures:",
                            "Errors:",
                            "Skipped:",
                            "AssertionError",
                            "AssertionFailedError",
                            "ComparisonFailure",
                            "Test failed",
                            "expected:",
                            "but was:",
                            "org.junit",
                            "org.testng",
                            "SurefireBooterForkException",
                            "ForkedBooter",
                            "The forked VM terminated",
                            "Process Exit Code",
                            "TestEngine",
                            "No tests were executed"
                    )
            ),

            new FailureRule(
                    FailureType.PLUGIN_EXECUTION,
                    List.of(
                            "PluginExecutionException",
                            "PluginResolutionException",
                            "PluginManagerException",
                            "MojoExecutionException",
                            "MojoFailureException",
                            "PluginConfigurationException",
                            "PluginContainerException",
                            "NoPluginFoundForPrefixException",
                            "Failed to execute goal",
                            "Error configuring plugin",
                            "Plugin not found",
                            "No plugin found for prefix",
                            "Unable to load the mojo",
                            "Unable to parse configuration",
                            "Invalid plugin configuration",
                            "Goal requires a project to execute",
                            "LifecycleExecutionException",
                            "Execution default",
                            "Execution failed"
                    )
            ),

            new FailureRule(
                    FailureType.APPLICATION_STARTUP,
                    List.of(
                            "BeanCreationException",
                            "UnsatisfiedDependencyException",
                            "NoSuchBeanDefinitionException",
                            "NoUniqueBeanDefinitionException",
                            "ApplicationContextException",
                            "ApplicationFailedEvent",
                            "Application run failed",
                            "SpringApplication",
                            "Error creating bean",
                            "Failed to start bean",
                            "Failed to configure a DataSource",
                            "Cannot determine embedded database driver",
                            "CannotGetJdbcConnectionException",
                            "JdbcConnectionException",
                            "DataAccessResourceFailureException",
                            "ConnectException",
                            "Port already in use",
                            "Address already in use",
                            "Failed to bind properties",
                            "ConfigurationPropertiesBindException",
                            "BindException",
                            "IllegalStateException",
                            "WebServerException",
                            "Tomcat started on port",
                            "Unable to start embedded Tomcat",
                            "Failed to start component",
                            "Context initialization failed",
                            "Failed to load ApplicationContext"
                    )
            ),

            new FailureRule(
                    FailureType.PACKAGING,
                    List.of(
                            "Failed to execute goal org.apache.maven.plugins:maven-jar-plugin",
                            "Failed to execute goal org.apache.maven.plugins:maven-war-plugin",
                            "Failed to execute goal org.apache.maven.plugins:maven-ear-plugin",
                            "Failed to execute goal org.apache.maven.plugins:maven-assembly-plugin",
                            "Failed to execute goal org.apache.maven.plugins:maven-shade-plugin",

                            "JarCreationException",
                            "ArchiverException",
                            "AssemblyFormattingException",
                            "AssemblyReadException",
                            "ShadeException",

                            "Unable to build jar",
                            "Unable to build war",
                            "Unable to create archive",
                            "Error assembling JAR",
                            "Error assembling WAR",
                            "Error creating assembly",
                            "Failed to package project",
                            "Archive creation failed",
                            "ManifestException",
                            "Invalid manifest",
                            "Duplicate entry",
                            "ZipException",
                            "Error while creating archive"
                    )
            ),

            new FailureRule(
                    FailureType.DEPLOYMENT,
                    List.of(
                            "Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin",
                            "DeployMojo",
                            "ArtifactDeploymentException",
                            "DeploymentException",
                            "Failed to deploy artifacts",
                            "Failed to deploy artifact",
                            "Could not deploy artifact",
                            "Error deploying artifact",
                            "TransferFailedException",
                            "RepositoryAuthenticationException",
                            "Authentication failed",
                            "Authorization failed",
                            "401 Unauthorized",
                            "403 Forbidden",
                            "407 Proxy Authentication Required",
                            "Repository does not exist",
                            "Could not transfer artifact",
                            "Failed to transfer file",
                            "Connection refused",
                            "Connection timed out",
                            "UnknownHostException",
                            "SSLHandshakeException",
                            "PKIX path building failed",
                            "Read timed out",
                            "Broken pipe"
                    )
            )
    );

    public FailureType classify(String[] lines) {
        for(FailureRule rule : rules) {
            for(String line : lines) {
                if(rule.matches(line)) {
                    return rule.getFailureType();
                }
            }
        }

        return FailureType.UNKNOWN;
    }
}
