package com.example.workmanager.service;


import com.example.workmanager.model.RailSwitch;
import com.example.workmanager.model.ToBeCheckSwitch;

import java.util.List;

public interface RailSwitchService {
    List<RailSwitch> getRailSwitchList();

    List<ToBeCheckSwitch> partitionateCheckingListInSixChunks();

    //boolean markRailSwitchAsChecked(String id, String checkCommment);
}
