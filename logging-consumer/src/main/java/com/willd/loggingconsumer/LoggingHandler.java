package com.willd.loggingconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoggingHandler {
    private final KafkaConsumer<String, String> loggingConsumer;

    public LoggingHandler(KafkaConsumer<String, String> loggingConsumer) {
        this.loggingConsumer = loggingConsumer;
        this.consume();
    }

    public void consume() {
        Thread cosumerThread = new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = loggingConsumer.poll(Duration.ofMillis(100));
                if (records == null) continue;
                for (ConsumerRecord<String, String> record : records) {
                    handle(record);
                }
            }
        });
        cosumerThread.start();
    }

    public void handle(ConsumerRecord<String, String> record) {
        if (record.value() == null) return;
        System.out.println("Received message -> " + "< " + record.key() + " >" + record.value());
    }
}
