package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.AnimalV1;
import com.saojudas.adocao.v1.domain.PessoaV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepositoryV1 extends JpaRepository<AnimalV1, Integer> {

    Optional<AnimalV1> findByIdAnimal(Integer idAnimal);
    AnimalV1 save(AnimalV1 animal);
    void deleteByIdAnimal(Integer idAnimal);
}
