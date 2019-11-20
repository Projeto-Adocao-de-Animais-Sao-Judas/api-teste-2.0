package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.EstadoV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepositoryV1 extends JpaRepository<EstadoV1, Integer> {

    Optional<EstadoV1> findByIdEstado(Integer idEstado);
    EstadoV1 save(EstadoV1 estado);
    void deleteByIdEstado(Integer idEstado);
}
