package ru.digitalleague.backend.generationmessgesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersItem {

    private UUID uuid;
    private Long cost;
    @Value(value = "#{T(java.time.LocalDate).now()}")
    private LocalDate dateCreate;
    private String status;

}
