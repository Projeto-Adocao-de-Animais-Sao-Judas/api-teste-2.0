package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.FotoPessoaV1;
import com.saojudas.adocao.v1.dto.FotoPessoaDTOV1;
import com.saojudas.adocao.v1.repository.FotoPessoaRepositoryV1;
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
public class FotoPessoaServiceV1 {

    private final FotoPessoaRepositoryV1 fotoFotoPessoaRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<FotoPessoaDTOV1>>() {}.getType();

    public FotoPessoaDTOV1 buscarFotoPessoaPorId(Integer idFotoPessoa) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<FotoPessoaV1> fotoFotoPessoa =  fotoFotoPessoaRepositoryV1.findByIdFotoPessoa(idFotoPessoa);

            if (fotoFotoPessoa.isPresent()) {
                FotoPessoaDTOV1 fotoFotoPessoaDTOV1 = modelMapper.map(fotoFotoPessoa.get(), FotoPessoaDTOV1.class);
                return fotoFotoPessoaDTOV1;
            } else {
                throw new ResourceNotFoundException("fotoFotoPessoa", "id", String.valueOf(idFotoPessoa));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<FotoPessoaDTOV1> buscarFotoPessoas() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalFotoPessoas = fotoFotoPessoaRepositoryV1.count();

            if (totalFotoPessoas > 0) {
                List<FotoPessoaV1> fotoFotoPessoas = fotoFotoPessoaRepositoryV1.findAll();
                List<FotoPessoaDTOV1> fotoFotoPessoasDTOV1 = modelMapper.map(fotoFotoPessoas, listType);
                return fotoFotoPessoasDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public FotoPessoaDTOV1 addFotoPessoa(FotoPessoaV1 fotoFotoPessoa) throws DatabaseException {
        try {
            FotoPessoaV1 fotoFotoPessoaV1 = fotoFotoPessoaRepositoryV1.save(fotoFotoPessoa);
            FotoPessoaDTOV1 fotoFotoPessoaDTOV1 = modelMapper.map(fotoFotoPessoaV1, FotoPessoaDTOV1.class);
            return fotoFotoPessoaDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateFotoPessoa(FotoPessoaDTOV1 fotoFotoPessoa) throws DatabaseException {
        try {
            FotoPessoaV1 fotoFotoPessoaDTOV1 = modelMapper.map(fotoFotoPessoa, FotoPessoaV1.class);
            fotoFotoPessoaRepositoryV1.save(fotoFotoPessoaDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerFotoPessoaPorId(Integer idFotoPessoa) throws DatabaseException {
        try {
            fotoFotoPessoaRepositoryV1.deleteByIdFotoPessoa(idFotoPessoa);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
