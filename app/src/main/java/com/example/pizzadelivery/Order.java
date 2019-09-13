package com.example.pizzadelivery;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    Double baseprice;
    Double toppingsprice;
    ArrayList<String> toppings=new ArrayList<>();
    Double deliverycost;
    Double totalCost;

    public Order(Double baseprice, Double toppingsprice, ArrayList<String> toppings, Double deliverycost, Double totalCost) {
        this.baseprice = baseprice;
        this.toppingsprice = toppingsprice;
        this.toppings = toppings;
        this.deliverycost = deliverycost;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "baseprice=" + baseprice +
                ", toppingsprice=" + toppingsprice +
                ", toppings=" + toppings +
                ", deliverycost=" + deliverycost +
                ", totalCost=" + totalCost +
                '}';
    }
}
