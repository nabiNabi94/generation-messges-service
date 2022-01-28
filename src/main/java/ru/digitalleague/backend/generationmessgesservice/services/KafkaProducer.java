package ru.digitalleague.backend.generationmessgesservice.services;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.digitalleague.backend.generationmessgesservice.model.User;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaProducer {
    @Value(value = "${spring.kafka.template.default-topic}")
    private static String TOPIC;
    private  Gson gson;
    private  KafkaTemplate<String, String> template;
    private static final Logger LOG = LogManager.getLogger(KafkaProducer.class);



    public KafkaProducer(Gson gson, KafkaTemplate<String, String> template) {
        this.gson = gson;
        this.template = template;
    }


    public void sendMessages(User user) throws ExecutionException, InterruptedException {
        String uuid = UUID.randomUUID().toString();
        user.getOrdersItems().forEach(item -> item.setDateCreate(LocalDate.now()));
        String userToJson = gson.toJson(user);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC,userToJson);
        record.headers().add("requestId", uuid.getBytes(StandardCharsets.UTF_8));
        ListenableFuture<SendResult<String, String>> user1 = this.template.send(record);
        LOG.info("Sending massages to kafka topic={} partition={} key={} value={} headers={} timestamp={}"
                ,record.topic(),record.partition(),record.key(),record.value(),record.headers(),record.timestamp());
    }
}
