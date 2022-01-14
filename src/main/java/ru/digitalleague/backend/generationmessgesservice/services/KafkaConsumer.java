package ru.digitalleague.backend.generationmessgesservice.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {

    private final Logger LOG = LogManager.getLogger(KafkaConsumer.class);

    @KafkaListener(topics ="vn-project-out",groupId = "my_groups")
    public void getMessage(ConsumerRecord<String,String> record){
       LOG.info("Getting answer from kafka topic={} partition={} key={} value={} headers={} timestamp={} offset={}" ,
               record.topic(),
               record.partition(),
               record.key(),
               record.value(),
               record.headers(),
               record.timestamp(),
               record.offset());
    }
}
