package com.gabriel.practice.Cilinders;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CilindersService {

    private final CilindersRepository cilindersRepository;

    public CilindersService (CilindersRepository cilindersRepository) {
        this.cilindersRepository = cilindersRepository;
    }

    public List<CilindersEntity> getCilinders () {
        return cilindersRepository.findAll();
    }

    public CilindersEntity getCilindersById (Long id) {
        return cilindersRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El cilindro con el id: " + id + " no se encontró"));
    }

    public CilindersEntity saveCilinder (CilindersEntity cilinder) {
        return cilindersRepository.save(cilinder);
    }

    public CilindersEntity updateCilinder (Long id, CilindersEntity updatedCilinder) {
        CilindersEntity existingCilinder = cilindersRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El cilindro con el id: " + id + " no se encontró"));
        existingCilinder.setCodigo(updatedCilinder.getCodigo());
        existingCilinder.setColor(updatedCilinder.getColor());
        existingCilinder.setProduct(updatedCilinder.getProduct());
        existingCilinder.setTotalMetros(updatedCilinder.getTotalMetros());
        
        return cilindersRepository.save(existingCilinder);
    }

    public void deleteCilinder (Long id) {
        CilindersEntity cilinder = cilindersRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El cilindro con el id: " + id + " no se encontró"));
        cilindersRepository.delete(cilinder);
    }
}
