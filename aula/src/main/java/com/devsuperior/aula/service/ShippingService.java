package com.devsuperior.aula.service;

import com.devsuperior.aula.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public double shipmentar(Order order) {
        double fee;
        double basic = order.getBasic();

        if (basic < 100.0) {
            fee = 20.0;
        } else if (basic <= 200.0) {
            fee = 10.0;
        } else {
            fee = 0.0;
        }
        return fee;
    }
}
