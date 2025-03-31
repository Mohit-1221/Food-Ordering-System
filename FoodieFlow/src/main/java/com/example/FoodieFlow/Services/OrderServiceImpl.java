package com.example.FoodieFlow.Services;

import com.example.FoodieFlow.Entity.Order;
import com.example.FoodieFlow.Entity.Restaurant;
import com.example.FoodieFlow.Entity.OrderStatus;
import com.example.FoodieFlow.Exceptions.OrderNotCompletedException;
import com.example.FoodieFlow.Exceptions.OrderNotFulfillForRestaurant;
import com.example.FoodieFlow.Exceptions.RestaurantNotFoundException;
import com.example.FoodieFlow.RestaurantSelection.OrderSelectionStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final List<Restaurant> restaurants = new ArrayList<>();
    private final HashMap<Integer, Order> orders = new HashMap<>();
    private int orderIdCounter = 1;

    @Override
    public void addRestaurant(String name, double rating, int maxOrder) {
        restaurants.add(new Restaurant(name, maxOrder, rating));
    }

    @Override
    public void addMenuItemsInRestaurant(String restaurantName, String itemName, double price) throws RestaurantNotFoundException {
        for(Restaurant restaurant : restaurants){
            if(restaurant.getName().equals(restaurantName)){
                restaurant.addMenuItems(itemName, price);
                return;
            }
        }
        throw new RestaurantNotFoundException("Restaurant Not Found!!!");
    }

    @Override
    public int placeOrder(List<String> items, OrderSelectionStrategy strategy) throws OrderNotFulfillForRestaurant {
        Restaurant selectedRestaurant = strategy.selectRestaurant(restaurants, items);
        if(selectedRestaurant == null){
            throw new OrderNotFulfillForRestaurant("No restaurant can fulfill this order.");
        }
        int orderId = orderIdCounter++;
        orders.put(orderId, new Order(orderId, items, selectedRestaurant));
        selectedRestaurant.setCurrOrders(selectedRestaurant.getCurrOrders() + 1);
        return orderId;
    }

    @Override
    public void completeOrder(int orderId) throws OrderNotCompletedException {
        Order order = orders.get(orderId);
        if (order == null || order.getStatus() != OrderStatus.ACCEPTED) {
            throw new OrderNotCompletedException("Invalid order completion request.");
        }
        order.setStatus(OrderStatus.COMPLETED);
        order.getRestaurant().setCurrOrders(order.getRestaurant().getCurrOrders() - 1);
    }
}
