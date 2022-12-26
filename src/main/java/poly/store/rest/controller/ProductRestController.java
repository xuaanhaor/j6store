package poly.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.store.entity.Product;
import poly.store.service.ProductService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @GetMapping()
    public List<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping()
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
