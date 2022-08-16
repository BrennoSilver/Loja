package com.project.loja.service;

import com.project.loja.entity.ClienteEntity;
import com.project.loja.exception.ProductNullException;
import com.project.loja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteEntity save(ClienteEntity cliente) {
        return repository.addCliente(cliente);
    }

    public ClienteEntity findById(Long id) {
        ClienteEntity cliente = repository.findById(id);
        if (cliente != null) {
            return cliente;
        }
        else{
            return null;
        }
    }

    public List<ClienteEntity> findAll() {
        return repository.findAll();

    }

    public ClienteEntity update(final Long id) {
        final ClienteEntity cliente = repository.findById(id);
        final ClienteEntity clienteEntity;

        if (cliente != null) {
            clienteEntity =  cliente;
        } else {
            throw new ProductNullException();
        }
        clienteEntity.setNome(clienteEntity.getNome());
        clienteEntity.setEndereco(clienteEntity.getEndereco());
        return repository.addCliente(clienteEntity);
    }

    public ClienteEntity delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        var clienteOptional = repository.findById(id);
        if (clienteOptional == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteBYId(id);
        redirectAttributes.addFlashAttribute("message", "Cliente removido com sucesso.");
        return null;
    }
}
