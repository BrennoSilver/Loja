package com.project.loja.repository;

import com.project.loja.entity.ClienteEntity;
import com.project.loja.sql.ClienteSql;
import org.skife.jdbi.v2.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class  ClienteRepository{
    @Autowired

   // Handle handle = null;

    ClienteSql clienteSql = null;


    public List<ClienteEntity> findAll() {
        return clienteSql.getClientes();
    }

    public ClienteEntity addCliente(ClienteEntity clienteEntity) {
        return clienteSql.addCliente(clienteEntity);
    }


    public ClienteEntity findById(Long id) {
        return clienteSql.getClienteById(id);
    }

    public ClienteEntity deleteBYId(Long id) {
        return clienteSql.deleteById(id);
    }


}
