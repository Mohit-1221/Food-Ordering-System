package com.example.FoodieFlow.RestaurantSelection;

import com.example.FoodieFlow.Entity.Restaurant;

import java.util.List;

public class HighestRatingSelection implements OrderSelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<String> items) {
        double maxRating = Double.MIN_VALUE;
        Restaurant bestRestaurant = null;
        for(Restaurant restaurant : restaurants){
            if(restaurant.canFulFillOrder(items) && restaurant.getCurrOrders() < restaurant.getMaxOrders()) {
                double rating = restaurant.getRating();
                if (rating > maxRating) {
                    maxRating = rating;
                    bestRestaurant = restaurant;
                }
            }
        }
        return bestRestaurant;
    }
}
