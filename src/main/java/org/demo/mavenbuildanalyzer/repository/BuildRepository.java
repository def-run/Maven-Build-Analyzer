package org.demo.mavenbuildanalyzer.repository;

import org.demo.mavenbuildanalyzer.entity.Build;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildRepository extends JpaRepository<Build, Long> {
}
