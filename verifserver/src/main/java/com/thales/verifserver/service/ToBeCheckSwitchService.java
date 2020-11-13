package com.thales.verifserver.service;

import com.thales.verifserver.model.ToBeCheckSwitch;

import java.util.List;

public interface ToBeCheckSwitchService {
    List<ToBeCheckSwitch> getCurrentPartition(long currentGroupId) ;

    ToBeCheckSwitch getFirst();

    boolean markAsChecked(ToBeCheckSwitch obj);

    void populateWithPartitionedSwitchList(List<ToBeCheckSwitch> partitionedSwitchList);
}
