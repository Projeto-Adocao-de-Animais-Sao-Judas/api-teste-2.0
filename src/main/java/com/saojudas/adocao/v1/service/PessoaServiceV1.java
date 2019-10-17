package com.saojudas.adocao.v1.service;


import com.saojudas.adocao.exceptions.DatabaseTimeoutException;
import com.saojudas.adocao.exceptions.EmptyRepositoryException;
import com.saojudas.adocao.exceptions.ResourceNotFoundException;
import com.saojudas.adocao.v1.domain.PessoaV1;
import com.saojudas.adocao.v1.dto.PessoaDTOV1;
import com.saojudas.adocao.v1.repository.PessoaRepositoryV1;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaServiceV1 {

    private final PessoaRepositoryV1 pessoaRepositoryV1;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<PessoaDTOV1>>() {}.getType();

    public PessoaDTOV1 buscarPessoaPorId(Integer id) throws DatabaseTimeoutException, ResourceNotFoundException {
        try {
            Optional<PessoaV1> pessoa =  pessoaRepositoryV1.findById(id);

            if (pessoa.isPresent()) {
                PessoaDTOV1 pessoaDTOV1 = modelMapper.map(pessoa.get(), PessoaDTOV1.class);
                return pessoaDTOV1;
            } else {
                throw new ResourceNotFoundException("pessoa", "id", String.valueOf(id));
            }
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao deu pra inserir");
        }
    }


    public PessoaV1 obterPessoa(Integer id) throws DatabaseTimeoutException, ResourceNotFoundException {
        try {
            Optional<PessoaV1> pessoa =  pessoaRepositoryV1.findById(id);

            if (pessoa.isPresent()) {
                return pessoa.get();
            } else {
                throw new ResourceNotFoundException("pessoa", "id", String.valueOf(id));
            }
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao deu pra inserir");
        }
    }


    public List<PessoaDTOV1> buscarPessoas() throws DatabaseTimeoutException, ResourceNotFoundException {
        try {
            Long totalPessoas = pessoaRepositoryV1.count();

            if (totalPessoas > 0) {
                List<PessoaV1> pessoas = pessoaRepositoryV1.findAll();
                List<PessoaDTOV1> pessoasDTOV1 = modelMapper.map(pessoas, listType);
                return pessoasDTOV1;
            } else {
                throw new EmptyRepositoryException("Ta vazio aqui");
            }
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao conectou");
        }
    }

    public PessoaDTOV1 addPessoa(PessoaV1 pessoa) throws DatabaseTimeoutException {
        try {
            PessoaV1 pessoaV1 = pessoaRepositoryV1.save(pessoa);
            PessoaDTOV1 pessoaDTOV1 = modelMapper.map(pessoaV1, PessoaDTOV1.class);
            return pessoaDTOV1;
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao deu pra inserir");
        }
    }

    public void updatePessoa(PessoaV1 pessoa) throws DatabaseTimeoutException {
        try {
            pessoaRepositoryV1.save(pessoa);
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao deu pra atualizar");
        }
    }

    public void removerPessoa(PessoaV1 pessoa) throws DatabaseTimeoutException {
        try {
            pessoaRepositoryV1.delete(pessoa);
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao deu pra deletar");
        }
    }

    public void removerPessoaPorId(Integer id) throws DatabaseTimeoutException {
        try {
            pessoaRepositoryV1.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseTimeoutException("nao deu pra remover por id");
        }
    }




}
