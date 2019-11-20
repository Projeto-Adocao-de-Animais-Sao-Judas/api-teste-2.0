package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.PessoaV1;
import com.saojudas.adocao.v1.dto.PessoaDTOV1;
import com.saojudas.adocao.v1.repository.PessoaRepositoryV1;
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
public class PessoaServiceV1 {

    private final PessoaRepositoryV1 pessoaRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<PessoaDTOV1>>() {}.getType();

    public PessoaDTOV1 buscarPessoaPorId(Integer idPessoa) throws DatabaseException, ResourceNotFoundException {
        try {
            Optional<PessoaV1> pessoa =  pessoaRepositoryV1.findByIdPessoa(idPessoa);

            if (pessoa.isPresent()) {
                PessoaDTOV1 pessoaDTOV1 = modelMapper.map(pessoa.get(), PessoaDTOV1.class);
                return pessoaDTOV1;
            } else {
                throw new ResourceNotFoundException("pessoa", "id", String.valueOf(idPessoa));
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<PessoaDTOV1> buscarPessoas() throws DatabaseException, ResourceNotFoundException {
        try {
            Long totalPessoas = pessoaRepositoryV1.count();

            if (totalPessoas > 0) {
                List<PessoaV1> pessoas = pessoaRepositoryV1.findAll();
                List<PessoaDTOV1> pessoasDTOV1 = modelMapper.map(pessoas, listType);
                return pessoasDTOV1;
            } else {
                throw new EmptyRepositoryException("Repositório vazio");
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public PessoaDTOV1 addPessoa(PessoaV1 pessoa) throws DatabaseException {
        try {
            PessoaV1 pessoaV1 = pessoaRepositoryV1.save(pessoa);
            PessoaDTOV1 pessoaDTOV1 = modelMapper.map(pessoaV1, PessoaDTOV1.class);
            return pessoaDTOV1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updatePessoa(PessoaDTOV1 pessoa) throws DatabaseException {
        try {
            PessoaV1 pessoaDTOV1 = modelMapper.map(pessoa, PessoaV1.class);
            pessoaRepositoryV1.save(pessoaDTOV1);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void removerPessoaPorId(Integer idPessoa) throws DatabaseException {
        try {
            pessoaRepositoryV1.deleteByIdPessoa(idPessoa);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }




}
