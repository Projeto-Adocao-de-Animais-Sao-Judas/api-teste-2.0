package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.FotoAnimalV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoAnimalRepositoryV1 extends JpaRepository<FotoAnimalV1, Integer> {

    Optional<FotoAnimalV1> findByIdFotoAnimal(Integer idFotoAnimal);
    FotoAnimalV1 save(FotoAnimalV1 fotoAnimal);
    void deleteByIdFotoAnimal(Integer idFotoAnimal);
}
