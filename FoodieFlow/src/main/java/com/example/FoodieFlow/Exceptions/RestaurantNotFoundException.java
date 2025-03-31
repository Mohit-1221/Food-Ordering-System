package com.example.FoodieFlow.Exceptions;

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
