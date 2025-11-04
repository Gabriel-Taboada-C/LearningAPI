package com.gabriel.practice.Colors;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List <ColorsEntity> getColors() {
        return colorsService.getColors();
    }

    @GetMapping("/{id}")
    public ColorsEntity getColorsById(@PathVariable Long id) {
        return colorsService.getColorsById(id);
    }

    @PostMapping("/{id}")
    public ColorsEntity createColor(@RequestBody ColorsEntity color) {
        
        return colorsService.saveColor(color);
    }

    @PutMapping("/{id}")
    public ColorsEntity updateColor(@PathVariable Long id, @RequestBody ColorsEntity color) {
        
        return colorsService.updateColor(id, color);
    }
    
    @DeleteMapping
    public String deleteColor (@PathVariable Long id) {
        colorsService.deleteColor(id);

        return "El color con el id " + id + " se eliminó correctamente.";
    }
    
    
}
