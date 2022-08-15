package com.project.loja.controller;


import com.project.loja.entity.ClienteEntity;
import com.project.loja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClienteEntity cadastrar(@RequestBody ClienteEntity cliente) {
        return repository.save(cliente);
    }

    @GetMapping
    public List<ClienteEntity> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ClienteEntity buscarPorId(@PathVariable Long id) {
        var clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clienteOptional.get();
    }

    @PutMapping("/{id}")
    public ClienteEntity atualizarPorId(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        var clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        return repository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        var clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.delete(clienteOptional.get());
    }

}
