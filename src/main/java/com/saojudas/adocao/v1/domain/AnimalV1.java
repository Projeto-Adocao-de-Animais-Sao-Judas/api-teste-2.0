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
@Table(name="animal")
public class AnimalV1 implements Serializable {


    private static final long serialVersionUID = -2891583101741255235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_animal")
    private Integer idAnimal;

    @Column(name="nome_animal")
    private String nomeAnimal;

    @Column(name="sexo_animal")
    private String sexoAnimal;

    @Column(name="idade_animal")
    private Integer idadeAnimal;

    @Column(name="tipo_animal")
    private String tipoAnimal;

    @Column(name="obs_animal")
    private String obsAnimal;

}
