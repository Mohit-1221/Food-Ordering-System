package com.example.FoodieFlow.Services;

import com.example.FoodieFlow.Entity.Restaurant;
import com.example.FoodieFlow.RestaurantSelection.OrderSelectionStrategy;

import java.util.List;

public interface OrderService {
    void addRestaurant(String name, double rating, int maxOrder);
    void addMenuItemsInRestaurant(String RestaurantName, String itemName, double price);
    int placeOrder(List<String> items, OrderSelectionStrategy strategy);
    void completeOrder(int orderId);

}
