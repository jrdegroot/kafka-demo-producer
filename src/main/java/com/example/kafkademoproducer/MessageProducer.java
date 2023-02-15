package com.example.kafkademoproducer;

import com.example.kafkademoproducer.model.Location;
import com.example.kafkademoproducer.model.User;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Utils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import static com.example.kafkademoproducer.config.KafkaTopicConfiguration.DEMO_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;
    private final Faker faker = new Faker();

    public void sendUser(User user) {
        kafkaTemplate.send(DEMO_TOPIC, user.getId(), user);
    }

    @Scheduled(fixedRateString = "${kafka.producer.rate}")
    public void produce() {

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .location(Location.randomLocation())
                .build();

        String partition = String.valueOf(Utils.toPositive(Utils.murmur2(user.getId().getBytes())) % 12);
        log.info("Partition: {}, Key: {}, User: {}", partition, user.getId(), user);
        sendUser(user);
    }

}
