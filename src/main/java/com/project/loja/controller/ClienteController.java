package com.project.loja.controller;


import com.project.loja.entity.ClienteEntity;
import com.project.loja.repository.ClienteRepository;
import com.project.loja.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;
    ClienteService clienteService = new ClienteService();

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClienteEntity cadastrar(@RequestBody ClienteEntity cliente) {
        return repository.addCliente(cliente);
    }

    @GetMapping
    public List<ClienteEntity> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ClienteEntity buscarPorId(@PathVariable Long id) {
        var clienteOptional = repository.findById(id);
        if (clienteOptional == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  clienteOptional;
    }

    @PutMapping("/{id}")
    public ClienteEntity atualizarPorId(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        return clienteService.update(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    clienteService.delete(id, redirectAttributes);
    }

}
