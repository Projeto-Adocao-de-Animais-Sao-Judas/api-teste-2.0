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
@Table(name="Endereco")
public class EnderecoV1 implements Serializable {

    private static final long serialVersionUID = 4379787308014646505L;

    @Column(name="id_endereco")
    private Integer idEndereco;

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

    @Column(name="fk_pessoa-endereco-id-pessoa")
    private Integer fkPessoaEnderecoIdPessoa;

    @Column(name="fk_cidade-endereco-id-cidade")
    private Integer fkCidadeEnderecoIdCidade;

    @Column(name="fk_estado_endereco_id_estado")
    private Integer fkEstadoEnderecoIdEstado;

}
