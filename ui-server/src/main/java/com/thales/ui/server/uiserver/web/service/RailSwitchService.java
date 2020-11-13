package com.thales.ui.server.uiserver.web.service;

import com.thales.ui.server.uiserver.web.domain.ToBeCheckSwitchDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RailSwitchService {
    List<RailSwitch> getRailSwitchList();

    List<ToBeCheckSwitchDto> getoBeCheckRailSwitchList();

    boolean markRailSwitchAsChecked(String id, String checkCommment);

    Long getGroupIdOfRailswitches();
}
