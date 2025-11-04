package com.gabriel.practice.Cilinders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<CilindersEntity> getCilinders() {
        return cilindersService.getCilinders();
    }

    @GetMapping("/{id}")
    public CilindersEntity getCilindersById(@PathVariable Long id) {
        return cilindersService.getCilindersById(id);
    }

    @PostMapping("/{id}")
    public CilindersEntity createCilinder (@RequestBody CilindersEntity cilinder) {
        
        return cilindersService.saveCilinder(cilinder);
    }

    @PutMapping("/{id}")
    public CilindersEntity updateCilinder(@PathVariable Long id, @RequestBody CilindersEntity cilinder) {

        return cilindersService.updateCilinder(id, cilinder);
    }

    @DeleteMapping
    public String deleteCilinder (@PathVariable Long id) {
        cilindersService.deleteCilinder(id);

        return "El cilindro con el id " + id + " se eliminó correctamente.";
    }
    
    
    
}
