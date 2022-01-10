package ru.digitalleague.backend.generationmessgesservice.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {

    @KafkaListener(topics ="vn-project-out",groupId = "my_groups")
    public void getMessage(ConsumerRecord<String,String> record){
        System.out.println(record.headers());
        System.out.println(record.value());
    }
}
