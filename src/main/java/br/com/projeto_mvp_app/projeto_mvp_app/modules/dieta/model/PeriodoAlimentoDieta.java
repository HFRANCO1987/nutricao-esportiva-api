package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.PeriodoAlimentoRequestList;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "PERIODO_ALIMENTO_DIETA")
public class PeriodoAlimentoDieta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "FK_DIETA", nullable = false)
    @JsonIgnore
    @ManyToOne
    private Dieta dieta;

    @JoinColumn(name = "FK_PERIODO", nullable = false)
    @ManyToOne
    private Periodo periodo;

    @JoinColumn(name = "FK_ALIMENTO", nullable = false)
    @ManyToOne
    private Alimento alimento;

    public static PeriodoAlimentoDieta of(Integer dietaId, PeriodoAlimentoRequestList request) {
        return PeriodoAlimentoDieta
            .builder()
            .alimento(new Alimento(request.getAlimentoId()))
            .dieta(new Dieta(dietaId))
            .periodo(new Periodo(request.getPeriodoId()))
            .build();
    }
}
