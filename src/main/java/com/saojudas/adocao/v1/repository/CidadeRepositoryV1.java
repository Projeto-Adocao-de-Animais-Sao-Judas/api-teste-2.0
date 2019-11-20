package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.CidadeV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepositoryV1 extends JpaRepository<CidadeV1, Integer> {

    Optional<CidadeV1> findByIdCidade(Integer idCidade);
    CidadeV1 save(CidadeV1 cidade);
    void deleteByIdCidade(Integer idCidade);
}
