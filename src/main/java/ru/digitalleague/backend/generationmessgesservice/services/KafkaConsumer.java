package ru.digitalleague.backend.generationmessgesservice.services;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digitalleague.backend.generationmessgesservice.model.User;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@Service
@EnableKafka
@RequiredArgsConstructor
public class KafkaConsumer {

    private final Logger LOG = LogManager.getLogger(KafkaConsumer.class);
    private Queue<String> queue = new ArrayDeque<>();

    private Gson gson;
    @KafkaListener(topics ="vn-project-out",groupId = "my_groups")
    public void getMessage(ConsumerRecord<String,String> record){
        queue.add(record.value());
       LOG.info("Getting answer from kafka topic={} partition={} key={} value={} headers={} timestamp={} offset={}" ,
               record.topic(),
               record.partition(),
               record.key(),
               record.value(),
               record.headers(),
               record.timestamp(),
               record.offset());
    }
    public User getAnswer(){
        User user = gson.fromJson(queue.poll(), User.class);
        return user;
    }

}
