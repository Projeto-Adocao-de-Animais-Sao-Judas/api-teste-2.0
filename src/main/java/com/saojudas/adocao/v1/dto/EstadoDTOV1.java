package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTOV1 implements Serializable {

    private static final long serialVersionUID = 3147577572091845257L;

    private Integer idEstado;

    private String nomeEstado;
}
