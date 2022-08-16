package com.project.loja.service;


import com.project.loja.entity.ProductEntity;
import com.project.loja.exception.ProductNullException;
import com.project.loja.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductEntity save(ProductEntity product) {
        return repository.addProduct(product);
    }

    public ProductEntity findById(Long id) {
        ProductEntity product = repository.findById(id);
        if (product != null) {
            return product;
        }
        else{
            return null;
        }
    }

    public List<ProductEntity> findAll() {
        return repository.findAll();

    }

    public ProductEntity update(final Long id) {
        final ProductEntity product = repository.findById(id);
        final ProductEntity productEntity;

        if (product != null) {
            productEntity =  product;
        } else {
            throw new ProductNullException();
        }
        productEntity.setNome(productEntity.getNome());
        productEntity.setPreco(productEntity.getPreco());
        productEntity.setDescricao(productEntity.getDescricao());
        return repository.addProduct(productEntity);
    }

    public void delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        var productOptional = repository.findById(id);
        if (productOptional == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Product removido com sucesso.");
    }
}
