package com.saojudas.adocao.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cidade")
public class CidadeV1 implements Serializable {

    private static final long serialVersionUID = 2457581417715401243L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cidade")
    private Integer idCidade;

    @Column(name="nome_cidade")
    private String nomeCidade;

}


