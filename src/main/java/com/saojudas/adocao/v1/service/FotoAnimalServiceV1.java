package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.FotoAnimalV1;
import com.saojudas.adocao.v1.dto.FotoAnimalDTOV1;
import com.saojudas.adocao.v1.repository.FotoAnimalRepositoryV1;
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
public class FotoAnimalServiceV1 {

    private final FotoAnimalRepositoryV1 fotoAnimalRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<FotoAnimalDTOV1>>() {}.getType();

    public FotoAnimalDTOV1 buscarFotoAnimalPorId(Integer idFotoAnimal) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<FotoAnimalV1> fotoAnimal =  fotoAnimalRepositoryV1.findByIdFotoAnimal(idFotoAnimal);

            if (fotoAnimal.isPresent()) {
                FotoAnimalDTOV1 fotoAnimalDTOV1 = modelMapper.map(fotoAnimal.get(), FotoAnimalDTOV1.class);
                return fotoAnimalDTOV1;
            } else {
                throw new ResourceNotFoundException("fotoAnimal", "id", String.valueOf(idFotoAnimal));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<FotoAnimalDTOV1> buscarFotoAnimais() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalFotoAnimais = fotoAnimalRepositoryV1.count();

            if (totalFotoAnimais > 0) {
                List<FotoAnimalV1> fotoAnimais = fotoAnimalRepositoryV1.findAll();
                List<FotoAnimalDTOV1> fotoAnimaisDTOV1 = modelMapper.map(fotoAnimais, listType);
                return fotoAnimaisDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public FotoAnimalDTOV1 addFotoAnimal(FotoAnimalV1 fotoAnimal) throws DatabaseException {
        try {
            FotoAnimalV1 fotoAnimalV1 = fotoAnimalRepositoryV1.save(fotoAnimal);
            FotoAnimalDTOV1 fotoAnimalDTOV1 = modelMapper.map(fotoAnimalV1, FotoAnimalDTOV1.class);
            return fotoAnimalDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateFotoAnimal(FotoAnimalDTOV1 fotoAnimal) throws DatabaseException {
        try {
            FotoAnimalV1 fotoAnimalDTOV1 = modelMapper.map(fotoAnimal, FotoAnimalV1.class);
            fotoAnimalRepositoryV1.save(fotoAnimalDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerFotoAnimalPorId(Integer idFotoAnimal) throws DatabaseException {
        try {
            fotoAnimalRepositoryV1.deleteByIdFotoAnimal(idFotoAnimal);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
