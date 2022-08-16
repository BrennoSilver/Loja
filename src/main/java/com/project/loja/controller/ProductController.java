package com.project.loja.controller;


import com.project.loja.entity.ProductEntity;
import com.project.loja.repository.ProductRepository;
import com.project.loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    ProductService productService = new ProductService();

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductEntity cadastrar(@RequestBody ProductEntity product) {
        return repository.addProduct(product);
    }

    @GetMapping
    public List<ProductEntity> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity buscarPorId(@PathVariable Long id) {
        var productOptional = repository.findById(id);
        if (productOptional == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productOptional;
    }

    @PutMapping("/{id}")
    public ProductEntity atualizarPorId(@PathVariable Long id, @RequestBody ProductEntity product) {
        return productService.update(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.delete(id, redirectAttributes);
    }
}
