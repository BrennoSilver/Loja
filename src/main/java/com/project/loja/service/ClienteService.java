package com.project.loja.service;

import com.project.loja.entity.ClienteEntity;
import com.project.loja.exception.ProductNullException;
import com.project.loja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteEntity save(ClienteEntity cliente) {
        return repository.save(cliente);
    }

    public ClienteEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ClienteEntity> findAll() {
        return repository.findAll();

    }

    public ClienteEntity update(final Long id) {
        final Optional<ClienteEntity> cliente = repository.findById(id);
        final ClienteEntity clienteEntity;

        if (cliente.isPresent()) {
            clienteEntity = cliente.get();
        } else {
            throw new ProductNullException();
        }
        clienteEntity.setNome(clienteEntity.getNome());
        clienteEntity.setEndereco(clienteEntity.getEndereco());

        return repository.save(clienteEntity);
    }

    public ClienteEntity delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, ClienteEntity cliente) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Cliente removido com sucesso.");
        return cliente;
    }
}
