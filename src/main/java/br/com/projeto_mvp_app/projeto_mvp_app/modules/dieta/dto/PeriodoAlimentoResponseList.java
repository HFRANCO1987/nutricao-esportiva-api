package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoAlimentoResponseList {

    private String alimento;
    private String categoria;
    private String periodo;

    public static PeriodoAlimentoResponseList of(PeriodoAlimentoDieta periodoAlimentoDieta) {
        return PeriodoAlimentoResponseList
            .builder()
            .alimento(periodoAlimentoDieta.getAlimento().getDescricao())
            .categoria(periodoAlimentoDieta.getAlimento().getCategoria().getDescricao())
            .periodo(periodoAlimentoDieta.getPeriodo().getDescricao())
            .build();
    }
}
