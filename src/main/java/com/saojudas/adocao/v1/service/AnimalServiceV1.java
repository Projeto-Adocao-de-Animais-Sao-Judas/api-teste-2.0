package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.AnimalV1;
import com.saojudas.adocao.v1.dto.AnimalDTOV1;
import com.saojudas.adocao.v1.repository.AnimalRepositoryV1;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AnimalServiceV1 {

    private final AnimalRepositoryV1 animalRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<AnimalDTOV1>>() {}.getType();

    public AnimalDTOV1 buscarAnimalPorId(Integer idAnimal) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<AnimalV1> animal =  animalRepositoryV1.findByIdAnimal(idAnimal);

            if (animal.isPresent()) {
                AnimalDTOV1 animalDTOV1 = modelMapper.map(animal.get(), AnimalDTOV1.class);
                return animalDTOV1;
            } else {
                throw new ResourceNotFoundException("animal", "id", String.valueOf(idAnimal));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<AnimalDTOV1> buscarAnimais() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalAnimais = animalRepositoryV1.count();

            if (totalAnimais > 0) {
                List<AnimalV1> animais = animalRepositoryV1.findAll();
                List<AnimalDTOV1> animaisDTOV1 = modelMapper.map(animais, listType);
                return animaisDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public AnimalDTOV1 addAnimal(AnimalV1 animal) throws DatabaseException {
        try {
            AnimalV1 animalV1 = animalRepositoryV1.save(animal);
            AnimalDTOV1 animalDTOV1 = modelMapper.map(animalV1, AnimalDTOV1.class);
            return animalDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateAnimal(AnimalDTOV1 animal) throws DatabaseException {
        try {
            AnimalV1 animalDTOV1 = modelMapper.map(animal, AnimalV1.class);
            animalRepositoryV1.save(animalDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerAnimalPorId(Integer idAnimal) throws DatabaseException {
        try {
            animalRepositoryV1.deleteByIdAnimal(idAnimal);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}