package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DietaResponse {

    private Dieta dieta;
    private List<PeriodoAlimentoDieta> alimentosDaDieta;

    public static DietaResponse of(Dieta dieta, List<PeriodoAlimentoDieta> alimentosDaDieta) {
        return DietaResponse
            .builder()
            .dieta(dieta)
            .alimentosDaDieta(alimentosDaDieta)
            .build();
    }
}
