package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.EnderecoV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepositoryV1 extends JpaRepository<EnderecoV1, Integer> {

    Optional<EnderecoV1> findByIdEndereco(Integer idEndereco);
    EnderecoV1 save(EnderecoV1 endereco);
    void deleteByIdEndereco(Integer idEndereco);
}
