package com.saojudas.adocao.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tipo_documento")
public class TipoDocumentoV1 implements Serializable {

    private static final long serialVersionUID = 1742259582331349897L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name="fk_pessoa_tipo_documento_id_pessoa")
    private Integer fkPessoaTipoDocumentoIdPessoa;

    @Column(name="nome_documento")
    private String nomeDocumento;

    @Column(name="numero_tipo_documento")
    private String numeroTipoDocumento;

}

