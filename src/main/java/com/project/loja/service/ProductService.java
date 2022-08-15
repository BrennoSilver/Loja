package com.project.loja.service;

import com.project.loja.entity.ProductEntity;
import com.project.loja.exception.ProductNullException;
import com.project.loja.exception.ProductPriceException;
import com.project.loja.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductEntity save(ProductEntity product) throws Exception {
        if (product.getNome() == null || product.getPreco() == null) throw new ProductPriceException();
        if (product.getPreco() < 0 ) throw new ProductPriceException();
        return repository.save(product);
    }

    public  ProductEntity findById(Long id){
        return repository.findById(id).orElse(null);
    }
    public List<ProductEntity> findAll(){
        return repository.findAll();

    }

    public ProductEntity update(final Long id)  {
        final Optional<ProductEntity> product = repository.findById(id);
        final ProductEntity productEntity;

        if (product.isPresent()) {
            productEntity = product.get();
        } else {
            throw new ProductNullException();
        }

        productEntity.setNome(productEntity.getNome());
        productEntity.setDescricao(productEntity.getDescricao());

        return repository.save(productEntity);
    }

    public ProductEntity delete(@PathVariable("id")  Long id, RedirectAttributes redirectAttributes, ProductEntity product) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Product removido com sucesso.");
        return product;
    }
}
