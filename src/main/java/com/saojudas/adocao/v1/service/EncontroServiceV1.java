package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.EncontroV1;
import com.saojudas.adocao.v1.dto.EncontroDTOV1;
import com.saojudas.adocao.v1.repository.EncontroRepositoryV1;
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
public class EncontroServiceV1 {

    private final EncontroRepositoryV1 encontroRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<EncontroDTOV1>>() {}.getType();

    public EncontroDTOV1 buscarEncontroPorId(Integer idEncontro) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<EncontroV1> encontro =  encontroRepositoryV1.findByIdEncontro(idEncontro);

            if (encontro.isPresent()) {
                EncontroDTOV1 encontroDTOV1 = modelMapper.map(encontro.get(), EncontroDTOV1.class);
                return encontroDTOV1;
            } else {
                throw new ResourceNotFoundException("encontro", "id", String.valueOf(idEncontro));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<EncontroDTOV1> buscarEncontros() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalEncontros = encontroRepositoryV1.count();

            if (totalEncontros > 0) {
                List<EncontroV1> encontros = encontroRepositoryV1.findAll();
                List<EncontroDTOV1> encontrosDTOV1 = modelMapper.map(encontros, listType);
                return encontrosDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public EncontroDTOV1 addEncontro(EncontroV1 encontro) throws DatabaseException {
        try {
            EncontroV1 encontroV1 = encontroRepositoryV1.save(encontro);
            EncontroDTOV1 encontroDTOV1 = modelMapper.map(encontroV1, EncontroDTOV1.class);
            return encontroDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateEncontro(EncontroDTOV1 encontro) throws DatabaseException {
        try {
            EncontroV1 encontroDTOV1 = modelMapper.map(encontro, EncontroV1.class);
            encontroRepositoryV1.save(encontroDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerEncontroPorId(Integer idEncontro) throws DatabaseException {
        try {
            encontroRepositoryV1.deleteByIdEncontro(idEncontro);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
