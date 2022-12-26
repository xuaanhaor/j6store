package poly.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.store.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import poly.store.entity.Category;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll(){
        return categoryService.findAll();
    }
}
