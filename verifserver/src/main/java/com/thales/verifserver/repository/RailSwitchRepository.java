package com.thales.verifserver.repository;

import com.thales.verifserver.model.RailSwitch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link RailSwitch} entity.
 */
public interface RailSwitchRepository extends JpaRepository<RailSwitch, Integer> {
}
