package poly.store.rest.controller;


import poly.store.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.store.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    public Order create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }
}
