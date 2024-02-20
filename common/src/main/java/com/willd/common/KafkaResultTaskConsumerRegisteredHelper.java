package com.willd.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaResultTaskConsumerRegisteredHelper {
    private final String bootstrapServers;

    @Getter
    private final String topic;

    public KafkaResultTaskConsumerRegisteredHelper(
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            @Value("${task.result.topic:null}") String topic
    ) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
    }

    public Properties createPropsConsumerTaskResultTopic() {
        if (bootstrapServers == null || topic == null) throw new RuntimeException("server environment is not exist please setting environment");
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
