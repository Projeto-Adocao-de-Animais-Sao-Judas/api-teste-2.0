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
@Table(name="encontro")
public class EncontroV1 implements Serializable {

    private static final long serialVersionUID = -2090889377803488445L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_encontro")
    private Integer idEncontro;

    @Column(name="fk_animal_encontro_id_animal")
    private Integer fkAnimalEncontroIdAnimal;

    @Column(name="fk_pessoa_encontro_id_pessoa")
    private Integer fkPessoaEncontroIdPessoa;



}


