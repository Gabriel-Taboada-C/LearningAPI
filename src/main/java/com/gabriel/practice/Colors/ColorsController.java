package com.gabriel.practice.Colors;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping ("/colors")
public class ColorsController {

    private final ColorsService colorsService;

    public ColorsController (ColorsService colorsService) {
        this.colorsService = colorsService;
    }

    @GetMapping
    public ResponseEntity<List<ColorsEntity>> getColors() {
        return ResponseEntity.ok(colorsService.getColors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorsEntity> getColorsById(@PathVariable Long id) {
        return ResponseEntity.ok(colorsService.getColorsById(id));
    }

    @PostMapping()
    public ResponseEntity<ColorsEntity> createColor(@RequestBody ColorsEntity color) {

        ColorsEntity saved = colorsService.saveColor(color);
        return ResponseEntity.status(HttpStatus.CREATED).body(colorsService.saveColor(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorsEntity> updateColor(@PathVariable Long id, @RequestBody ColorsEntity color) {

        return ResponseEntity.ok(colorsService.updateColor(id, color));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor (@PathVariable Long id) {
        colorsService.deleteColor(id);

        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
    
    
}
