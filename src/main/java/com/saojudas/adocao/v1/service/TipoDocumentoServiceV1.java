package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.TipoDocumentoV1;
import com.saojudas.adocao.v1.dto.TipoDocumentoDTOV1;
import com.saojudas.adocao.v1.repository.TipoDocumentoRepositoryV1;
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
public class TipoDocumentoServiceV1 {

    private final TipoDocumentoRepositoryV1 tipoDocumentoRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<TipoDocumentoDTOV1>>() {}.getType();

    public TipoDocumentoDTOV1 buscarTipoDocumentoPorId(Integer idTipoDocumento) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<TipoDocumentoV1> tipoDocumento =  tipoDocumentoRepositoryV1.findByIdTipoDocumento(idTipoDocumento);

            if (tipoDocumento.isPresent()) {
                TipoDocumentoDTOV1 tipoDocumentoDTOV1 = modelMapper.map(tipoDocumento.get(), TipoDocumentoDTOV1.class);
                return tipoDocumentoDTOV1;
            } else {
                throw new ResourceNotFoundException("tipoDocumento", "id", String.valueOf(idTipoDocumento));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<TipoDocumentoDTOV1> buscarTipoDocumentos() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalTipoDocumentos = tipoDocumentoRepositoryV1.count();

            if (totalTipoDocumentos > 0) {
                List<TipoDocumentoV1> tipoDocumentos = tipoDocumentoRepositoryV1.findAll();
                List<TipoDocumentoDTOV1> tipoDocumentosDTOV1 = modelMapper.map(tipoDocumentos, listType);
                return tipoDocumentosDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TipoDocumentoDTOV1 addTipoDocumento(TipoDocumentoV1 tipoDocumento) throws DatabaseException {
        try {
            TipoDocumentoV1 tipoDocumentoV1 = tipoDocumentoRepositoryV1.save(tipoDocumento);
            TipoDocumentoDTOV1 tipoDocumentoDTOV1 = modelMapper.map(tipoDocumentoV1, TipoDocumentoDTOV1.class);
            return tipoDocumentoDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateTipoDocumento(TipoDocumentoDTOV1 tipoDocumento) throws DatabaseException {
        try {
            TipoDocumentoV1 tipoDocumentoDTOV1 = modelMapper.map(tipoDocumento, TipoDocumentoV1.class);
            tipoDocumentoRepositoryV1.save(tipoDocumentoDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerTipoDocumentoPorId(Integer idTipoDocumento) throws DatabaseException {
        try {
            tipoDocumentoRepositoryV1.deleteByIdTipoDocumento(idTipoDocumento);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
