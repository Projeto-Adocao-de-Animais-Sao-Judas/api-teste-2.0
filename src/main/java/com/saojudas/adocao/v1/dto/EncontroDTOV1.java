package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncontroDTOV1 implements Serializable {

    private static final long serialVersionUID = 6475828507778224730L;

    private Integer idEncontro;

    private Integer fkAnimalEncontroIdAnimal;

    private Integer fkPessoaEncontroIdPessoa;
}
