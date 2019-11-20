package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.CidadeV1;
import com.saojudas.adocao.v1.dto.CidadeDTOV1;
import com.saojudas.adocao.v1.repository.CidadeRepositoryV1;
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
public class CidadeServiceV1 {

    private final CidadeRepositoryV1 cidadeRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<CidadeDTOV1>>() {}.getType();

    public CidadeDTOV1 buscarCidadePorId(Integer idCidade) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<CidadeV1> cidade =  cidadeRepositoryV1.findByIdCidade(idCidade);

            if (cidade.isPresent()) {
                CidadeDTOV1 cidadeDTOV1 = modelMapper.map(cidade.get(), CidadeDTOV1.class);
                return cidadeDTOV1;
            } else {
                throw new ResourceNotFoundException("cidade", "id", String.valueOf(idCidade));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<CidadeDTOV1> buscarCidades() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalCidades = cidadeRepositoryV1.count();

            if (totalCidades > 0) {
                List<CidadeV1> cidades = cidadeRepositoryV1.findAll();
                List<CidadeDTOV1> cidadesDTOV1 = modelMapper.map(cidades, listType);
                return cidadesDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public CidadeDTOV1 addCidade(CidadeV1 cidade) throws DatabaseException {
        try {
            CidadeV1 cidadeV1 = cidadeRepositoryV1.save(cidade);
            CidadeDTOV1 cidadeDTOV1 = modelMapper.map(cidadeV1, CidadeDTOV1.class);
            return cidadeDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateCidade(CidadeDTOV1 cidade) throws DatabaseException {
        try {
            CidadeV1 cidadeDTOV1 = modelMapper.map(cidade, CidadeV1.class);
            cidadeRepositoryV1.save(cidadeDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerCidadePorId(Integer idCidade) throws DatabaseException {
        try {
            cidadeRepositoryV1.deleteByIdCidade(idCidade);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
