package com.gabriel.practice.Products;

import java.util.List;

import org.springframework.stereotype.Service;

/* Controlador con @Service
 * Aquí el controlador delega toda la lógica de negocio a un servicio:
 *  Características:
 *
 * Separación de responsabilidades:
 *
 * El controlador solo maneja HTTP (peticiones, respuestas, códigos de estado).
 *
 * El servicio maneja la lógica de negocio (validaciones, reglas, cálculos, etc.).
 *
 * El repositorio maneja el acceso a la base de datos.
 *
 * Más fácil de probar unitariamente → podés testear el servicio sin levantar un servidor HTTP.
 *
 * Si mañana necesitás exponer la misma lógica por otra vía 
 * (por ejemplo, un batch o un evento de Kafka), podés reutilizar el @Service
 * sin depender del controlador.
 */

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService (ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /* Logica del GetMapping Individual */
    public ProductsEntity getProductById (Long id) {
        return productsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El producto con el id: " + id + " no se encontró."));
    }

    /* Logica del GetMapping Grupal */
    public List <ProductsEntity> getAllProducts() {
        return productsRepository.findAll();
    }

    /* Logica del PostMapping */
    public ProductsEntity saveProduct(ProductsEntity product) {
        return productsRepository.save(product);
    }
    
    /* Logica del PutMappin */
    public ProductsEntity updateProduct (Long id, ProductsEntity updatedProduct) {
        ProductsEntity existingProduct = productsRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("El producto con el id: " + id + " no se encontró."));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setWide(updatedProduct.getWide());
        existingProduct.setLongitud(updatedProduct.getLongitud());
        existingProduct.setCilinders(updatedProduct.getCilinders());
        existingProduct.setImpMaterial(updatedProduct.getImpMaterial());
        existingProduct.setLamMaterial(updatedProduct.getLamMaterial());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setEntryDay(updatedProduct.getEntryDay());

        return productsRepository.save(existingProduct);
    }
    
    /* Logica del DeleteMapping */    
    public void deleteProduct(Long id) {
        ProductsEntity product = productsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El producto con el id: " + id + " no se encontró."));
        productsRepository.delete(product);
    }
}
