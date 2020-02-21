package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

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
@Table(name = "ALIMENTO")
@SuppressWarnings("MemberName")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "FK_CATEGORIA")
    private Categoria categoria;
    @Column
    private String descricao;
    @Column
    private Double umidade;
    @Column
    private Double energia_kcal;
    @Column
    private Double energia_kj;
    @Column
    private Double protei_g;
    @Column
    private Double lipideos_g;
    @Column
    private Double colesterol_mg;
    @Column
    private Double carboidrato_g;
    @Column
    private Double fibra_alimentar_g;
    @Column
    private Double cinzas_g;
    @Column
    private Double calcio_mg;
    @Column
    private Double magnesio_mg;
    @Column
    private Integer numero_alimento;
    @Column
    private Double manganes_mg;
    @Column
    private Double fosforo_mg;
    @Column
    private Double ferro_mg;
    @Column
    private Double sodio_mg;
    @Column
    private Double potassio_mg;
    @Column
    private Double cobre_mg;
    @Column
    private Double zinco_mg;
    @Column
    private Double retinol_mcg;
    @Column
    private Double re_mcg;
    @Column
    private Double era_mcg;
    @Column
    private Double tiami_mg;
    @Column
    private Double riboflavi_mg;
    @Column
    private Double piridoxi_mg;
    @Column
    private Double niaci_mg;
    @Column
    private Double vitami_c_mg;
    @Column
    private String imagem;

    public Alimento(Integer id) {
        this.id = id;
    }
}
