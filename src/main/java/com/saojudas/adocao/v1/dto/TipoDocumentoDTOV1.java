package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoDTOV1 implements Serializable {

    public static final long serialVersionUID = -7523924461663327259L;

    private Integer idTipoDocumento;

    private Integer fkPessoaTipoDocumentoIdPessoa;

    private String nomeDocumento;

    private String numeroTipoDocumento;
}
