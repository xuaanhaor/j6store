package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.store.entity.Product;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {
    @Query("select p from Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(String cid);
}