package org.demo.mavenbuildanalyzer.repository;

import org.demo.mavenbuildanalyzer.entity.BuildException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildExceptionRepository extends JpaRepository<BuildException, Long> {
}
