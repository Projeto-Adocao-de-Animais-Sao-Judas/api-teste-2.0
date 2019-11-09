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
@Table(name="Estado")
public class EstadoV1 implements Serializable {

    private static final long serialVersionUID = 3975361879343984511L;

    @Column(name="id_estado")
    private Integer idEstado;

    @Column(name="nome_estado")
    private String nomeEstado;
}
