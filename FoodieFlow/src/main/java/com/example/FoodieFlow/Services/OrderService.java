package com.example.FoodieFlow.Services;

import com.example.FoodieFlow.Entity.Restaurant;
import com.example.FoodieFlow.Exceptions.OrderNotCompletedException;
import com.example.FoodieFlow.Exceptions.OrderNotFulfillForRestaurant;
import com.example.FoodieFlow.Exceptions.RestaurantNotFoundException;
import com.example.FoodieFlow.RestaurantSelection.OrderSelectionStrategy;

import java.util.List;

public interface OrderService {
    void addRestaurant(String name, double rating, int maxOrder);
    void addMenuItemsInRestaurant(String RestaurantName, String itemName, double price) throws RestaurantNotFoundException;
    int placeOrder(List<String> items, OrderSelectionStrategy strategy) throws OrderNotFulfillForRestaurant;
    void completeOrder(int orderId) throws OrderNotCompletedException;

}
