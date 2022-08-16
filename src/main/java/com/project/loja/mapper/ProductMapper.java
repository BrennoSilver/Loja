package com.project.loja.mapper;

import com.project.loja.entity.ProductEntity;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultSetMapper<ProductEntity> {
    @Override
    public ProductEntity map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ProductEntity(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getDouble("preco"), resultSet.getString("descricao"));
    }

}