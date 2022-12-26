package poly.store.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import poly.store.entity.Order;

import java.util.List;


@Service
public interface OrderService {

    Order create(JsonNode orderData);

    Order findById(Long id);

    List<Order> findByUsername(String username);
}
