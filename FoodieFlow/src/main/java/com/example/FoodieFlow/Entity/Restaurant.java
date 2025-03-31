package com.example.FoodieFlow.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Restaurant {
    public String name;
    int maxOrders;
    double rating;
    List<Menu> menuList;
    int currOrders;

    public Restaurant(String name, int maxOrders, double rating) {
        this.name = name;
        this.maxOrders = maxOrders;
        this.rating = rating;
        this.menuList = new ArrayList<>();
        this.currOrders = 0;
    }

    public void addMenuItems(String name, double price){
        menuList.add(new Menu(name, price));
    }
    public boolean canFulFillOrder(List<String> items){
        Set<String> menu = new HashSet<>();
        for(Menu menuItems : menuList){
            menu.add(menuItems.name);
        }
        return menu.containsAll(items);
    }
    public double calculateOrderCost(List<String> items){
        double cost = 0;
        for(String item : items){
            for(Menu menu : menuList){
                if(menu.name.equals(item)){
                    cost += menu.price;
                }
            }
        }
        return cost;
    }
}
