package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.EstadoV1;
import com.saojudas.adocao.v1.dto.EstadoDTOV1;
import com.saojudas.adocao.v1.repository.EstadoRepositoryV1;
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
public class EstadoServiceV1 {

    private final EstadoRepositoryV1 estadoRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<EstadoDTOV1>>() {}.getType();

    public EstadoDTOV1 buscarEstadoPorId(Integer idEstado) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<EstadoV1> estado =  estadoRepositoryV1.findByIdEstado(idEstado);

            if (estado.isPresent()) {
                EstadoDTOV1 estadoDTOV1 = modelMapper.map(estado.get(), EstadoDTOV1.class);
                return estadoDTOV1;
            } else {
                throw new ResourceNotFoundException("estado", "id", String.valueOf(idEstado));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<EstadoDTOV1> buscarEstados() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalEstados = estadoRepositoryV1.count();

            if (totalEstados > 0) {
                List<EstadoV1> estados = estadoRepositoryV1.findAll();
                List<EstadoDTOV1> estadosDTOV1 = modelMapper.map(estados, listType);
                return estadosDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public EstadoDTOV1 addEstado(EstadoV1 estado) throws DatabaseException {
        try {
            EstadoV1 estadoV1 = estadoRepositoryV1.save(estado);
            EstadoDTOV1 estadoDTOV1 = modelMapper.map(estadoV1, EstadoDTOV1.class);
            return estadoDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateEstado(EstadoDTOV1 estado) throws DatabaseException {
        try {
            EstadoV1 estadoDTOV1 = modelMapper.map(estado, EstadoV1.class);
            estadoRepositoryV1.save(estadoDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerEstadoPorId(Integer idEstado) throws DatabaseException {
        try {
            estadoRepositoryV1.deleteByIdEstado(idEstado);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
