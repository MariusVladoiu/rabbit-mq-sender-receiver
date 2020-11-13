package com.thales.verifserver.repository;

import com.thales.verifserver.model.CheckedSwitch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link CheckedSwitch} entity.
 */
public interface CheckedSwitchRepository extends JpaRepository<CheckedSwitch, Integer> {
}
