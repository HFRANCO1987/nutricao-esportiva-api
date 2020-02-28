package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ESPORTE")
public class Esporte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "ATIVIDADE", nullable = false)
    private String atividade;

    @Column(name = "KCAL_HORA_MIN", nullable = false)
    private Double kcalHoraMin;

    @Column(name = "KCAL_HORA_MAX", nullable = false)
    private Double kcalHoraMax;

    @Column(name = "KCAL_MIN_MIN", nullable = false)
    private Double kcalMinMin;

    @Column(name = "KCAL_MIN_MAX", nullable = false)
    private Double kcalMinMax;

    public Esporte(Integer id) {
        this.id = id;
    }
}
