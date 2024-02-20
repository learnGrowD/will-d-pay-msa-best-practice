package com.willd.tastconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TaskResultProducer {

    private final KafkaProducer<String, String> producer;

    private final ObjectMapper objectMapper;

    private final String topic;

    public TaskResultProducer(
            ObjectMapper objectMapper,
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${task.result.topic}") String topic
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.objectMapper = objectMapper;
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void sendTaskResultWithThrow(String key, Object task) throws Exception {
        String jsonStringToProducer;
        try {
            jsonStringToProducer = objectMapper.writeValueAsString(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProducer);
        producer.send(record, (metadata, error) -> {
            if (error == null) {
                System.out.println("Success");
            } else {
                error.printStackTrace();
                throw new RuntimeException(error);
            }
        });
    }
}

