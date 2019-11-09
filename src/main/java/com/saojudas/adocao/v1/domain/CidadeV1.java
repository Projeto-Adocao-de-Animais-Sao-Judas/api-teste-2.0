package com.saojudas.adocao.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Cidade")
public class CidadeV1 implements Serializable {

    private static final long serialVersionUID = 2457581417715401243L;

    @Column(name="id_cidade")
    private Integer idCidade;

    @Column(name="nome_cidade")
    private String nomeCidade;

}


