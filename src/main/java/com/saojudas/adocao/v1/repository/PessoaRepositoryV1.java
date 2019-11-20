package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.PessoaV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepositoryV1 extends JpaRepository<PessoaV1, Integer> {

    Optional<PessoaV1> findByIdPessoa(Integer idPessoa);
    PessoaV1 save(PessoaV1 pessoa);
    void deleteByIdPessoa(Integer idPessoa);
}
