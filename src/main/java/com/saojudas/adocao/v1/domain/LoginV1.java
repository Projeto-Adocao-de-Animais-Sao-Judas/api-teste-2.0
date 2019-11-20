package com.saojudas.adocao.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="login")

public class LoginV1  implements Serializable {

    private static final long serialVersionUID = -1180716267851448943L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_login")
    private Integer idLogin;

    @Column(name="fk_pessoa_login_id_pessoa")
    private Integer fkPessoaLoginIdPessoa;

    @Column(name="email_login")
    private String emailLogin;

    @Column(name="senha_login")
    private String senhaLogin;


}

