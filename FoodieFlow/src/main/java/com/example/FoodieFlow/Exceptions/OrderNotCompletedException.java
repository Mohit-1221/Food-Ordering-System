package com.example.FoodieFlow.Exceptions;

public class OrderNotCompletedException extends Exception{
    public OrderNotCompletedException(String message){
        super(message);
    }
}
