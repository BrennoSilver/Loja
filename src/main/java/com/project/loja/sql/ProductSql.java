package com.project.loja.sql;


import com.project.loja.entity.ProductEntity;
import com.project.loja.mapper.ProductMapper;
import org.hibernate.annotations.SQLDelete;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(value = ProductMapper.class)
public interface ProductSql {

    @SqlQuery("Select * from Product_TB")
    List<ProductEntity> getProduct();

    @SqlUpdate("insert into Product_TB ( nome, preco, descricao)"
            + "value(:nome,:preco, :descricao)")
    @GetGeneratedKeys
    ProductEntity addProduct(@BindBean ProductEntity product);

    @SqlQuery("SELECT * FROM Product_TB WHERE id = :id")
    ProductEntity getProductById(@Bind("id") Long id);

    @SQLDelete(sql = "DELETE FROM Product_TB(id)" + "value(:id)")
    ProductEntity deleteById(@Bind("id") Long id);

}
