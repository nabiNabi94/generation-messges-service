//package ru.digitalleague.backend.generationmessgesservice.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import ru.digitalleague.backend.generationmessgesservice.services.KafkaProducer;
//import ru.digitalleague.backend.generationmessgesservice.model.User;
//
//import java.util.concurrent.ExecutionException;
//
//@RestController
//public class KafkaController {
//    private KafkaProducer kafkaProducer;
//
//    @Autowired
//    private User user;
//
//    public KafkaController(KafkaProducer kafkaProducer) {
//        this.kafkaProducer = kafkaProducer;
//    }
//
//    @GetMapping("/")
//    public String sendMessages() throws ExecutionException, InterruptedException {
//        this.kafkaProducer.sendMessages(user);
//        return "OK";
//    }
//}
