package com.gabriel.practice.Inks;

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
@RequestMapping("/inks")
public class InksController {

    private final InksService inksService;

    public InksController (InksService inksService) {
        this.inksService = inksService;
    }

    @GetMapping
    public ResponseEntity<List<InksEntity>> getInks() {
        return ResponseEntity.ok(inksService.getInks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InksEntity> getInksById(@PathVariable Long id) {
        return ResponseEntity.ok(inksService.getInksById(id));
    }

    @PostMapping()
    public ResponseEntity<InksEntity> createInk (@RequestBody InksEntity ink) {

        InksEntity saved = inksService.saveInk(ink);
        return ResponseEntity.status(HttpStatus.CREATED).body(inksService.saveInk(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InksEntity> updateInk(@PathVariable Long id, @RequestBody InksEntity ink) {

        return ResponseEntity.ok(inksService.updateInk(id, ink));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInk (@PathVariable Long id) {
        inksService.deleteInk(id);

        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
    
}
