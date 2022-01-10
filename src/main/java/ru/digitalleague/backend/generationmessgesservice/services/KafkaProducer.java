package ru.digitalleague.backend.generationmessgesservice.services;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.digitalleague.backend.generationmessgesservice.model.User;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@EnableScheduling
public class KafkaProducer {
    private static final String TOPIC = "vn-project-in";
    private  Gson gson;
    private  KafkaTemplate<String, String> template;
    private User user;

    long random = new Random()
            .longs(50, 5000)
            .findFirst()
            .getAsLong();

    public KafkaProducer(Gson gson, KafkaTemplate<String, String> template, User user) {
        this.gson = gson;
        this.template = template;
        this.user = user;
    }

    @Scheduled(fixedRate = 50000)
    public void sendMessages() throws ExecutionException, InterruptedException {
        String uuid = UUID.randomUUID().toString();
        String userToJson = gson.toJson(user);
        System.out.println(userToJson);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, "User", userToJson);
        record.headers().add("requestId", uuid.getBytes(StandardCharsets.UTF_8));
        ListenableFuture<SendResult<String, String>> user1 = this.template.send(record);
    }
}
