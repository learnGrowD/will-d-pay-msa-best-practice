package com.willd.tastconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.tasks.DecreaseMoneyTask;
import com.willd.common.tasks.IncreaseMoneyTask;
import com.willd.common.tasks.TaskCategory;
import com.willd.tastconsumer.adapter.out.persistence.RequestTaskHistoryJpaEntity;
import com.willd.tastconsumer.domain.RequestTaskHistoryStatus;
import com.willd.tastconsumer.application.port.out.RequestTaskHistoryPort;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class TaskConsumer {
    private final KafkaConsumer<String, String> consumer;

    public TaskConsumer(
            RequestTaskHistoryPort requestTaskHistoryPort,
            TaskResultProducer taskResultProducer,
            ObjectMapper objectMapper,
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${task.topic}") String topic
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        Thread cosumerThread = new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        RequestTaskHistoryJpaEntity entity = requestTaskHistoryPort.create(record.key());
                        try {
                            String increaseMoneyDomain = TaskCategory.INCREASE_MONEY.getDomain();
                            String decreaseMoneyDomain = TaskCategory.DECREASE_MONEY.getDomain();
                            if (record.key().equals(increaseMoneyDomain)) {
                                IncreaseMoneyTask task = objectMapper.readValue(record.value(), IncreaseMoneyTask.class);
                                entity.setUuid(task.getUuid().toString());
                                taskResultProducer.sendTaskResultWithThrow(increaseMoneyDomain, task);
                            } else if (record.key().equals(decreaseMoneyDomain)) {
                                DecreaseMoneyTask task = objectMapper.readValue(record.value(), DecreaseMoneyTask.class);
                                entity.setUuid(task.getUuid().toString());
                                taskResultProducer.sendTaskResultWithThrow(decreaseMoneyDomain, task);
                            }
                            entity.setStatus(RequestTaskHistoryStatus.SUCCESS);
                            requestTaskHistoryPort.modify(entity);
                        } catch (Exception e) {
                            entity.setStatus(RequestTaskHistoryStatus.FAIL);
                            requestTaskHistoryPort.modify(entity);
                            throw new RuntimeException(e);
                        }
                    }
                }
            } finally {
                consumer.close();
            }
        });
        cosumerThread.start();
    }
}
