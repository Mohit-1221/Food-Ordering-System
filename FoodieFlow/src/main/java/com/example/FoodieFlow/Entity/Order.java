package com.example.FoodieFlow.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    int id;
    List<String> items;
    Restaurant restaurant;
    OrderStatus status;

    public Order(int id, List<String> items, Restaurant restaurant) {
        this.id = id;
        this.items = items;
        this.restaurant = restaurant;
        this.status = OrderStatus.ACCEPTED;
    }
}
