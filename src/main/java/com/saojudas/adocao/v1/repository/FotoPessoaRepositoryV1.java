package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.FotoPessoaV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoPessoaRepositoryV1 extends JpaRepository<FotoPessoaV1, Integer> {

    Optional<FotoPessoaV1> findByIdFotoPessoa(Integer idFotoPessoa);
    FotoPessoaV1 save(FotoPessoaV1 fotoPessoa);
    void deleteByIdFotoPessoa(Integer idFotoPessoa);
}
