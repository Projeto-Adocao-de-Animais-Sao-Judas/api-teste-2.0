package com.saojudas.adocao.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Foto_Pessoa")
public class FotoPessoaV1 implements Serializable {

    private static final long serialVersionUID = -3977148260989848666L;

    @Column(name="id_foto_pessoa")
    private Integer idFotoPessoa;

    @Column(name="fk_pessoa_foto_pessoa_id_pessoa")
    private Integer fkPessoaFotoPessoaIdPessoa;

    @Column(name="descricao_imagem")
    private String descricaoImagem;

    @Column(name="imagem")
    private String OID;


}

