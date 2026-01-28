package com.gabriel.practice.Products;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductsEntity>> getProducts() {

        return ResponseEntity.ok(productsService.getAllProducts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductsEntity> getProductsById (@PathVariable Long id) {

        return ResponseEntity.ok(productsService.getProductById(id));
    }

    @PostMapping()
    public ResponseEntity<ProductsEntity> createProduct(@RequestBody ProductsEntity product) {

        ProductsEntity saved = productsService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveProduct(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsEntity> updateProduct(@PathVariable Long id, @RequestBody ProductsEntity updatedProduct) {

        return ResponseEntity.ok(productsService.updateProduct(id, updatedProduct));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id) {

        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
    
}
