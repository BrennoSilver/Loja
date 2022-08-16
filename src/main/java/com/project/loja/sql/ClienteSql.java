package com.project.loja.sql;

import com.project.loja.entity.ClienteEntity;
import com.project.loja.mapper.ClienteMapper;
import org.hibernate.annotations.SQLDelete;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(value = ClienteMapper.class)
public interface ClienteSql {

    @SqlQuery("Select * from Cliente_TB")
     List<ClienteEntity> getClientes();

    @SqlUpdate("insert into Cliente_TB ( nome,endereco)"
            + "value(:nome,:endereco)")
    @GetGeneratedKeys
     ClienteEntity addCliente(@BindBean ClienteEntity cliente);

    @SqlQuery("SELECT * FROM Cliente_TB WHERE id = :id")
    ClienteEntity getClienteById(@Bind("id") Long id);

    @SQLDelete(sql = "DELETE FROM Cliente_TB(id)" + "value(:id)")
    ClienteEntity deleteById(@Bind("id") Long id);

}