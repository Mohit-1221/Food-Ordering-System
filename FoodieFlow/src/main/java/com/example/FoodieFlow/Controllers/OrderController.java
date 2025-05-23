package com.example.FoodieFlow.Controllers;

import com.example.FoodieFlow.Exceptions.OrderNotCompletedException;
import com.example.FoodieFlow.Exceptions.OrderNotFulfillForRestaurant;
import com.example.FoodieFlow.Exceptions.RestaurantNotFoundException;
import com.example.FoodieFlow.RestaurantSelection.HighestRatingSelection;
import com.example.FoodieFlow.RestaurantSelection.LowestCostSelection;
import com.example.FoodieFlow.RestaurantSelection.OrderSelectionStrategy;
import com.example.FoodieFlow.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/restaurant")
    public ResponseEntity addRestaurant(@RequestParam String name, @RequestParam double rating, @RequestParam int maxOrder){
        orderService.addRestaurant(name, rating, maxOrder);
        return ResponseEntity.ok("Restaurants Onboarded");
    }
    @PostMapping("/restaurant/addMenu")
    public ResponseEntity addMenuItemsInRestaurant(@RequestParam  String restaurantName, @RequestParam String itemName, @RequestParam double price) {
        try {
            orderService.addMenuItemsInRestaurant(restaurantName, itemName, price);
            return new ResponseEntity("Menu Item added to: " + restaurantName, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestParam List<String> items, @RequestParam String strategyType)  {
        OrderSelectionStrategy orderSelectionStrategy = strategyType.equals("lowest_cost") ? new LowestCostSelection() : new HighestRatingSelection();
        try {
            int orderId = orderService.placeOrder(items, orderSelectionStrategy);
            return ResponseEntity.ok("Order Placed with ID: " + orderId);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/complete ")
    public ResponseEntity completeOrder(@RequestParam int orderId) {
        try {
            orderService.completeOrder(orderId);
            return ResponseEntity.ok("Order marked as Completed!!");
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
