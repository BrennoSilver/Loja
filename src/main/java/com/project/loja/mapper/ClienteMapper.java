package com.project.loja.mapper;

import com.project.loja.entity.ClienteEntity;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper implements ResultSetMapper<ClienteEntity> {
    @Override
    public ClienteEntity map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ClienteEntity(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("endereco"));
    }

}
