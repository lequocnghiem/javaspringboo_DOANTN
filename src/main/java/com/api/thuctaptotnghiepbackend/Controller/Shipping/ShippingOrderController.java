package com.api.thuctaptotnghiepbackend.Controller.Shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.thuctaptotnghiepbackend.Entity.ShippingOrder;
import com.api.thuctaptotnghiepbackend.Service.ShippingOrderService;

@RestController
@CrossOrigin(origins = "*")
public class ShippingOrderController {

    private final ShippingOrderService shippingOrderService;

    @Autowired
    public ShippingOrderController(ShippingOrderService shippingOrderService) {
        this.shippingOrderService = shippingOrderService;
    }

    @PostMapping("/api/shipping-order")
    public ResponseEntity<String> createShippingOrder(@RequestBody ShippingOrder shippingOrder) {
        System.err.println(shippingOrder.getIdOrder());
        return shippingOrderService.createShippingOrder(shippingOrder);
    }



    @GetMapping("/api/shipping-order/{orderCode}")
    public ResponseEntity<String> getShippingOrderDetails(@PathVariable String orderCode) {
        ResponseEntity<String> response = shippingOrderService.getShippingOrderDetails(orderCode);
        return response;
    }
}
