package com.devsuperior.aula.service;

import com.devsuperior.aula.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ShippingService shippingService;

    public double total(Order order){
        double discount = order.getBasic() * (order.getDiscount() / 100);
        double shipment = shippingService.shipmentar(order);

        return (order.getBasic() -discount) + shipment;
    }
}
