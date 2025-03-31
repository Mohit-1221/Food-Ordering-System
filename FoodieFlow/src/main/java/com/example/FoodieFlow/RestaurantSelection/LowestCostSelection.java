package com.example.FoodieFlow.RestaurantSelection;

import com.example.FoodieFlow.Entity.Restaurant;

import java.util.List;

public class LowestCostSelection implements OrderSelectionStrategy {

    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<String> items) {
        double minCost = Double.MAX_VALUE;
        Restaurant bestRestaurant = null;
        for(Restaurant restaurant : restaurants){
            if(restaurant.canFulFillOrder(items) && restaurant.getCurrOrders() < restaurant.getMaxOrders()){
                double cost = restaurant.calculateOrderCost(items);
                if(cost < minCost) {
                    minCost = cost;
                    bestRestaurant = restaurant;
                }
            }
        }
        return bestRestaurant;
    }
}
