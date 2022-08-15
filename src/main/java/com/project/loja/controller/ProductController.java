package com.project.loja.controller;

import com.project.loja.entity.ProductEntity;
import com.project.loja.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductEntity cadastrar(@RequestBody ProductEntity Product) {
        return repository.save(Product);
    }

    @GetMapping
    public List<ProductEntity> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity buscarPorId(@PathVariable Long id) {
        var productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return productOptional.get();
    }

    @PutMapping("/{id}")
    public ProductEntity atualizarPorId(@PathVariable Long id, @RequestBody ProductEntity product) {
        var productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        var productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.delete(productOptional.get());
    }

}
