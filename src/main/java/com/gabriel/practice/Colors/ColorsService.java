package com.gabriel.practice.Colors;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ColorsService {

    private final ColorsRepository colorsRepository;

    public ColorsService (ColorsRepository colorsRepository) {
        this.colorsRepository = colorsRepository;
    }

    public List<ColorsEntity> getColors () {
        return colorsRepository.findAll();
    }

    public ColorsEntity getColorsById (Long id) {
        return colorsRepository.findById(id)
            .orElseThrow(()->new RuntimeException("El color con el id " + id + " no se encontró"));
    }

    public ColorsEntity saveColor (ColorsEntity color) {
        return colorsRepository.save(color);
    }

    public ColorsEntity updateColor (Long id, ColorsEntity updatedColor) {
        ColorsEntity existingColor = colorsRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El color con el id " + id + " no se encontró"));
        existingColor.setColor(updatedColor.getColor());
        existingColor.setHexCode(updatedColor.getHexCode());
        existingColor.setMark(updatedColor.getMark());
        existingColor.setCilindros(updatedColor.getCilindros());

        return colorsRepository.save(existingColor);
    }

    public void deleteColor (Long id) {
        ColorsEntity color = colorsRepository.findById(id)
            .orElseThrow(()->new RuntimeException("El color con el id " + id + " no se encontró"));
        colorsRepository.delete(color);
    }
}
