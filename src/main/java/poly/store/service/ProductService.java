package poly.store.service;


import org.springframework.stereotype.Service;
import poly.store.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService  {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);}
