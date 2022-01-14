package ru.digitalleague.backend.generationmessgesservice.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OrdersItem.class)
class OrdersItemTest {

    @Test
    void localFieldDateTestOrderItemsClass(){
        OrdersItem ordersItem = new OrdersItem();
        System.out.println(ordersItem);
    }

}