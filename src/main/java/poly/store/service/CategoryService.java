package poly.store.service;

import org.springframework.stereotype.Service;
import poly.store.entity.Category;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAll();
}
