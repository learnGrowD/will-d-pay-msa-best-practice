package com.willd.moneyservice.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.KafkaResultTaskConsumerRegisteredHelper;
import com.willd.common.tasks.DecreaseMoneyTask;
import com.willd.common.tasks.IncreaseMoneyTask;
import com.willd.common.tasks.TaskCategory;
import com.willd.moneyservice.application.port.in.MoneyUserCase;
import com.willd.moneyservice.application.port.in.command.DecreaseMemberMoneyCommand;
import com.willd.moneyservice.application.port.in.command.IncreaseMemberMoneyCommand;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
public class MoneyChargingConsumer {
    private final KafkaConsumer<String, String> consumer;

    public MoneyChargingConsumer(
            KafkaResultTaskConsumerRegisteredHelper consumerRegisteredHelper,
            ObjectMapper objectMapper,
            MoneyUserCase moneyUserCase
    ) {

        this.consumer = new KafkaConsumer<>(consumerRegisteredHelper.createPropsConsumerTaskResultTopic());
        this.consumer.subscribe(Collections.singletonList(consumerRegisteredHelper.getTopic()));

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            if (record.key().equals(TaskCategory.INCREASE_MONEY.getDomain())) {
                                IncreaseMoneyTask task = objectMapper.readValue(record.value(), IncreaseMoneyTask.class);
                                IncreaseMemberMoneyCommand command = IncreaseMemberMoneyCommand.builder()
                                        .targetMembershipId(task.getTargetMembershipId())
                                        .increaseMoneyAmount(task.getIncreaseMoneyAmount())
                                        .build();
                                moneyUserCase.increaseMemberMoney(command);
                            } else if (record.key().equals(TaskCategory.DECREASE_MONEY.getDomain())) {
                                DecreaseMoneyTask task = objectMapper.readValue(record.value(), DecreaseMoneyTask.class);
                                DecreaseMemberMoneyCommand command = DecreaseMemberMoneyCommand.builder()
                                        .targetMembershipId(task.getTargetMembershipId())
                                        .decreaseMoneyAmount(task.getDecreaseMoneyAmount())
                                        .build();
                                moneyUserCase.decreaseMemberMoney(command);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
