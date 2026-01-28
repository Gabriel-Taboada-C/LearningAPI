package com.gabriel.practice.Cilinders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/cilinders")
public class CilindersController {

    private final CilindersService cilindersService;

    public CilindersController (CilindersService cilindersService) {
        this.cilindersService = cilindersService;
    }

    @GetMapping
    public ResponseEntity<List<CilindersEntity>> getCilinders() {
        return ResponseEntity.ok(cilindersService.getCilinders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CilindersEntity> getCilindersById(@PathVariable Long id) {
        return ResponseEntity.ok(cilindersService.getCilindersById(id));
    }

    @PostMapping()
    public ResponseEntity<CilindersEntity> createCilinder (@RequestBody CilindersEntity cilinder) {

        CilindersEntity saved = cilindersService.saveCilinder(cilinder);
        return ResponseEntity.status(HttpStatus.CREATED).body(cilindersService.saveCilinder(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CilindersEntity> updateCilinder(@PathVariable Long id, @RequestBody CilindersEntity cilinder) {

        return ResponseEntity.ok(cilindersService.updateCilinder(id, cilinder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCilinder (@PathVariable Long id) {
        cilindersService.deleteCilinder(id);

        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
    
    
    
}
