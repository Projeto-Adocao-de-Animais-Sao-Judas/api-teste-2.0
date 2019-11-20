package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FotoPessoaDTOV1 implements Serializable {

    private static final long serialVersionUID = -5230346749300026333L;

    private Integer idFotoPessoa;

    private Integer fkPessoaFotoPessoaIdPessoa;

    private String descricaoImagem;

    private String imagemUrl;
}
