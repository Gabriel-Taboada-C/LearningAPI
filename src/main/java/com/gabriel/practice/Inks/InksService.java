package com.gabriel.practice.Inks;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InksService {

    private final InksRepository inksRepository;

    public InksService (InksRepository inksRepository) {
        this.inksRepository = inksRepository;
    }

    public List<InksEntity> getInks () {
        return inksRepository.findAll();
    }

    public InksEntity getInksById (Long id) {
        return inksRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("La tinta con el id: " + id + " no se encontro"));
    }

    public InksEntity saveInk (InksEntity ink) {
        return inksRepository.save(ink);
    }

    public InksEntity updateInk (Long id, InksEntity updatedInk) {
        InksEntity existingInk = inksRepository.findById (id)
                .orElseThrow(()-> new RuntimeException("La tinta con el id: " + id + " no se encontro"));
        existingInk.setMark(updatedInk.getMark());
        existingInk.setColor(updatedInk.getColor());
        existingInk.setCode(updatedInk.getCode());
        existingInk.setEntryDate(updatedInk.getEntryDate());

        return inksRepository.save(existingInk);
    }

    public void deleteInk (Long id) {
        InksEntity ink = inksRepository.findById (id)
                .orElseThrow(()-> new RuntimeException("La tinta con el id: " + id + " no se encontro"));
        inksRepository.delete(ink);
    }
}
