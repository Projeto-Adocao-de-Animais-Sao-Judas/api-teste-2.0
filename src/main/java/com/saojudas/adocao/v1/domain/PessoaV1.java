package com.saojudas.adocao.v1.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@Table(name="pessoa")
public class PessoaV1 implements Serializable {

    private static final long serialVersionUID = -2329540826512942631L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pessoa")
    private Integer idPessoa;

    @Column(name="fk_pessoa_tipo_documento_id_tipo_documento")
    private Integer fkPessoaTipoDocumentoIdTipoDocumento;

    @Column(name="fk_login_pessoa_id_login")
    private Integer fkLoginPessoaIdLogin;

    @Column(name="fk_endereco_pessoa_id_endereco")
    private Integer fkEnderecoPessoaIdEndereco;

    @Column(name="telefone_pessoa")
    private String telefonePessoa;

    @Column(name="nome_pessoa")
    private String nomePessoa;

    @Column(name="email_pessoa")
    private String emailPessoa;

    @Column(name="sexoPessoa")
    private String sexoPessoa;

}
