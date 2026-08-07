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
            )
    );
}
