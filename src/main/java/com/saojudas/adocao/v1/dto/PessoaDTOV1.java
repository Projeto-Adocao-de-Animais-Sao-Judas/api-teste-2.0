package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTOV1 implements Serializable {

    private static final long serialVersionUID = 1592208447572730365L;

    private Integer id;

    private String endereco;

    private String complemento;

    private String numero;

    private String cep;

    private String tel;

    private String cpf;

}
