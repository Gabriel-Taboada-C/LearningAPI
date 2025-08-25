package com.gabriel.practice.Products;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping ("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController (ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<ProductsEntity> getProducts() {

        return productsService.getAllProducts();
    }
    
    @GetMapping("/{id}")
    public ProductsEntity getProductsById (@PathVariable Long id) {
        
        return productsService.getProductById(id);
    }

    @PostMapping("/{id}")
    public ProductsEntity createProduct(@RequestBody ProductsEntity product) {
        
        return productsService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductsEntity updateProduct(@PathVariable Long id, @RequestBody ProductsEntity updatedProduct) {
        
        return productsService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping ("/{id}")
    public String deleteProduct (@PathVariable Long id) {

        productsService.deleteProduct(id);
        return "El producto con el id: " + id + " se eliminó correctamente.";
    }
    
}
