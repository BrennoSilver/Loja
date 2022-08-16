package com.project.loja.repository;

import com.project.loja.entity.ProductEntity;
import com.project.loja.sql.ProductSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository  {
    @Autowired

    // Handle handle = null;

    ProductSql productSql = null;


    public List<ProductEntity> findAll() {
        return productSql.getProduct();
    }

    public ProductEntity addProduct(ProductEntity product) {
        return productSql.addProduct(product);
    }


    public ProductEntity findById(Long id) {
        return productSql.getProductById(id);
    }

    public void deleteById(Long id) {
        productSql.deleteById(id);
    }
}
