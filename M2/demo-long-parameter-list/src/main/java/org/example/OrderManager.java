package org.example;

import java.util.List;

public class OrderManager {
    public double orderTotal(Order currentOrder) {
        double orderSum = 0.0;

        for (Item i: currentOrder.getItemList()
             ) {
            orderSum += i.getPrice();
        }

        orderSum = orderSum - (orderSum * currentOrder.getDiscountRate());

        orderSum += orderSum*currentOrder.getTaxRate();

        return orderSum;
    }
}
