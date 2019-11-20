package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.EncontroV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncontroRepositoryV1 extends JpaRepository<EncontroV1, Integer> {

    Optional<EncontroV1> findByIdEncontro(Integer idEncontro);
    EncontroV1 save(EncontroV1 encontro);
    void deleteByIdEncontro(Integer idEncontro);
}
