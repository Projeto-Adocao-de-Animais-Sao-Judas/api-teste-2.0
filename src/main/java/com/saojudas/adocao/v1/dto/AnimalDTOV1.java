package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTOV1 implements Serializable {

    private static final long serialVersionUID = -5792093296890737482L;

    private Integer idAnimal;

    private String nomeAnimal;

    private String sexoAnimal;

    private Integer idadeAnimal;

    private String tipoAnimal;

    private String obsAnimal;

}
