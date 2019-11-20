package com.saojudas.adocao.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="foto_animal")
public class FotoAnimalV1 implements Serializable {

    private static final long serialVersionUID = -6316430889462225580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idFotoAnimal")
    private Integer idFotoAnimal;

    @Column(name="fk_animal_foto_animal_id_animal")
    private Integer fkAnimalFotoAnimalIdAnimal;

    @Column(name="descricao_foto_animal")
    private String descricaoFotoAnimal;

    @Column(name="imagem_url")
    private String imagemUrl;


}
