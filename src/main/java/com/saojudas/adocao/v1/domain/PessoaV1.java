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
@Table(name="pessoa_tb")
public class PessoaV1 implements Serializable {

    private static final long serialVersionUID = -2329540826512942631L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id")
    private Integer id;

    @Column(name="endereco")
    private String endereco;

    @Column(name="complemento")
    private String complemento;

    @Column(name="numero")
    private String numero;

    @Column(name="cep")
    private String cep;

    @Column(name="tel")
    private String tel;

    @Column(name="cpf")
    private String cpf;

    @Column(name="timestamp")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String timestamp;

}
