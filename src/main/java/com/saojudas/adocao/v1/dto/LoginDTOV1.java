package com.saojudas.adocao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTOV1 implements Serializable {

    public static final long serialVersionUID = 1479291432573282326L;

    private Integer idLogin;

    private Integer fkPessoaLoginIdPessoa;

    private String emailLogin;

    private String senhaLogin;

}
