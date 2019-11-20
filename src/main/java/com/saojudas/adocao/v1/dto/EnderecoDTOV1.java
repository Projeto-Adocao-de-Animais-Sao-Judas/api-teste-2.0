package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTOV1 implements Serializable {

    private static final long serialVersionUID = 3404271928334933886L;

    private Integer idEndereco;

    private Integer fkPessoaEnderecoIdPessoa;

    private Integer fkCidadeEnderecoIdCidade;

    private Integer fkEstadoEnderecoIdEstado;

    private String endRua;

    private String endBairro;

    private String endCep;

    private Integer endNumero;

    private String endComplmento;

}
