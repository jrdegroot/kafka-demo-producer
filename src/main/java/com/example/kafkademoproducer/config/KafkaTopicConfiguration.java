package com.example.kafkademoproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    public final static String DEMO_TOPIC = "demo-topic";

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(DEMO_TOPIC)
                .partitions(12)
                .replicas(3)
                .build();
    }
}
