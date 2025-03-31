package com.example.FoodieFlow.RestaurantSelection;

import com.example.FoodieFlow.Entity.Restaurant;

import java.util.List;

public interface OrderSelectionStrategy {
     Restaurant selectRestaurant(List<Restaurant> restaurants, List<String> items);
}
