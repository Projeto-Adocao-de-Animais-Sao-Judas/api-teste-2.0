package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDTOV1 implements Serializable {

    private static final long serialVersionUID = 2051750673669589314L;

    private Integer idCidade;

    private String nomeCidade;
}
