package com.example.th2musicdownload.repository;

import com.example.th2musicdownload.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAll();
    Product findByProductCode(String productCode);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Product p set p.description = :description, p.price = :price where p.productCode = :productCode")
    void updateByCode(@Param("productCode") String productCode, @Param("description") String description, @Param("price") Double price);
}
