package ru.digitalleague.backend.generationmessgesservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.backend.generationmessgesservice.model.User;
import ru.digitalleague.backend.generationmessgesservice.services.KafkaConsumer;
import ru.digitalleague.backend.generationmessgesservice.services.KafkaProducer;

import java.util.concurrent.ExecutionException;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {

    private KafkaProducer kafkaProducer;
    private KafkaConsumer kafkaConsumer;

    @PostMapping("/")
    public ResponseEntity<User>chekOrdersUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        kafkaProducer.sendMessages(user);
       return ResponseEntity.ok().body(kafkaConsumer.getAnswer());

    }
}
