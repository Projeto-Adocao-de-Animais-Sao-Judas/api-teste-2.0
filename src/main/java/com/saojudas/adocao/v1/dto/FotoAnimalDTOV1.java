package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FotoAnimalDTOV1 implements Serializable {

    private static final long serialVersionUID = 3862105640946829175L;

    private Integer idFotoAnimal;

    private Integer fkAnimalFotoAnimalIdAnimal;

    private String descricaoFotoAnimal;

    private String imagemUrl;
}
