package com.willd.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TaskProducer {
    private final KafkaProducer<String, String> kafkaProducer;

    private final ObjectMapper objectMapper;

    private final String bootstrapServers;
    private final String topic;

    public TaskProducer(
            ObjectMapper objectMapper,
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            @Value("${task.topic:null}") String topic
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.objectMapper = objectMapper;
        this.kafkaProducer = new KafkaProducer<>(props);
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
    }

    public void sendTask(String key, Object value) {
        if (bootstrapServers == null || topic == null) throw new RuntimeException("server environment is not exist please setting environment");
        String jsonStringToProducer;
        try {
            jsonStringToProducer = objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProducer);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception == null) {
                // System.out.println("Message sent successfully. Offset: " + metadata.offset());
            } else {
                exception.printStackTrace();
                // System.err.println("Failed to send message: " + exception.getMessage());
            }
        });
    }
}
