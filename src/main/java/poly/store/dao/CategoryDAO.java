package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.store.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
}
