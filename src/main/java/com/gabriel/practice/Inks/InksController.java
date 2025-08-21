package com.gabriel.practice.Inks;

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
@RequestMapping("/inks")
public class InksController {

    private final InksService inksService;

    public InksController (InksService inksService) {
        this.inksService = inksService;
    }

    @GetMapping
    public List<InksEntity> getInks() {
        return inksService.getInks();
    }

    @GetMapping("/{id}")
    public InksEntity getInksById(@PathVariable Long id) {
        return inksService.getInksById(id);
    }

    @PostMapping("/{id}")
    public InksEntity createInk (@RequestBody InksEntity ink) {

        return inksService.saveInk(ink);
    }

    @PutMapping("/{id}")
    public InksEntity updateInk(@PathVariable Long id, @RequestBody InksEntity ink) {
        
        return inksService.updateInk(id, ink);
    }
    
    @DeleteMapping
    public String deleteInk (@PathVariable Long id) {
        inksService.deleteInk(id);

        return "La tinta con el id: " + id + " se elimino correctamente";
    }
    
}
