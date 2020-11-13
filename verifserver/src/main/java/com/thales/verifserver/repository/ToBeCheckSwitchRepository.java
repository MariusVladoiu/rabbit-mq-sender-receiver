package com.thales.verifserver.repository;

import com.thales.verifserver.model.ToBeCheckSwitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link ToBeCheckSwitch} entity.
 */
public interface ToBeCheckSwitchRepository extends JpaRepository<ToBeCheckSwitch, Long> {

    @Query("select u from ToBeCheckSwitch u where u.groupId = :groupId")
    List<ToBeCheckSwitch> findByGroupId(long groupId);
}
