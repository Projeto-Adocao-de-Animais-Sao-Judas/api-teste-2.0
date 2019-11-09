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
@Table(name="Encontro")

public class EncontroV1 implements Serializable {

    private static final long serialVersionUID = -2090889377803488445L;

    @Column(name="id_encontro")
    private Integer idEncontro;

    @Column(name="fk_animal_encontro_id_animal")
    private Integer fkAnimalEncontroIdAnimal;

    @Column(name="fk_pessoa_encontro_id_pessoa")
    private Integer fkPessoaEncontroIdPessoa;



}


