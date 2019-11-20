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
@Table(name="endereco")
public class EnderecoV1 implements Serializable {

    private static final long serialVersionUID = 4379787308014646505L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_endereco")
    private Integer idEndereco;

    @Column(name="fk_pessoa_endereco_id_pessoa")
    private Integer fkPessoaEnderecoIdPessoa;

    @Column(name="fk_cidade_endereco_id_cidade")
    private Integer fkCidadeEnderecoIdCidade;

    @Column(name="fk_estado_endereco_id_estado")
    private Integer fkEstadoEnderecoIdEstado;

    @Column(name="end_rua")
    private String endRua;

    @Column(name="end_bairro")
    private String endBairro;

    @Column(name="end_cep")
    private String endCep;

    @Column(name="end_numero")
    private Integer endNumero;

    @Column(name="end_complemento")
    private String endComplmento;

}
