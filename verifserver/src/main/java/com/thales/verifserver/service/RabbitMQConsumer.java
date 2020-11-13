package com.thales.verifserver.service;

import com.thales.verifserver.model.ToBeCheckSwitch;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RabbitMQConsumer {
    @Autowired
    private ToBeCheckSwitchService toBeCheckSwitchService;

    @RabbitListener(queues = "${railswitch.rabbitmq.queue}")
    public void recievedMessage(List<ToBeCheckSwitch> partitionedSwitchList) {
        System.out.println("Recieved Message From RabbitMQ: " + partitionedSwitchList.toString());

        toBeCheckSwitchService.populateWithPartitionedSwitchList(partitionedSwitchList);
    }
}