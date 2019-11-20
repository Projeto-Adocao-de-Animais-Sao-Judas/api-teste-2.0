package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.TipoDocumentoV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDocumentoRepositoryV1 extends JpaRepository<TipoDocumentoV1, Integer> {

    Optional<TipoDocumentoV1> findByIdTipoDocumento(Integer idTipoDocumento);
    TipoDocumentoV1 save(TipoDocumentoV1 tipoDocumento);
    void deleteByIdTipoDocumento(Integer idTipoDocumento);
}
