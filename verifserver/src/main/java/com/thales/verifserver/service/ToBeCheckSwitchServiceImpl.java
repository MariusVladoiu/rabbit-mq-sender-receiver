package com.thales.verifserver.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thales.verifserver.model.ToBeCheckSwitch;
import com.thales.verifserver.repository.ToBeCheckSwitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@Service
public class ToBeCheckSwitchServiceImpl implements ToBeCheckSwitchService {

    @Autowired
    private ToBeCheckSwitchRepository repository;


    @Override
    public List<ToBeCheckSwitch> getCurrentPartition(long currentGroupId) {
        return repository.findByGroupId(currentGroupId);
    }

    @Override
    public boolean markAsChecked(ToBeCheckSwitch obj) {
        Optional<ToBeCheckSwitch> ret = repository.findById(obj.getId());

        if( ret.isPresent() == false) return false;

        ToBeCheckSwitch updateObj = ret.get();
        updateObj.setChecked(true);
        updateObj.setCheckedComment(obj.getCheckedComment());
        repository.saveAndFlush(updateObj);

        return true;
    }
    @Override
    public void populateWithPartitionedSwitchList(List<ToBeCheckSwitch> partitionedSwitches) {
        ObjectMapper mapper = new ObjectMapper();

        List<ToBeCheckSwitch> partitionedSwitchList = mapper.convertValue(partitionedSwitches, new TypeReference<List<ToBeCheckSwitch>>() { });

        partitionedSwitchList.forEach(x -> {
                repository.saveAndFlush(x) ;
        });
    }

    @Override
    public ToBeCheckSwitch getFirst() {
        return repository.findAll().get(0);
    }
}
