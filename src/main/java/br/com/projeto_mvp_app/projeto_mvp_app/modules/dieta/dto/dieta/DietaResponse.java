package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Builder
public class DietaResponse {

    private Integer id;
    private String descricao;
    private List<PeriodoAlimentoResponseList> alimentosDaDieta;

    public static DietaResponse of(Dieta dieta, List<PeriodoAlimentoDieta> alimentosDaDieta) {
        return DietaResponse
            .builder()
            .id(dieta.getId())
            .descricao(dieta.getDescricao())
            .alimentosDaDieta(alimentosDaDieta.stream().map(PeriodoAlimentoResponseList::of).collect(toList()))
            .build();
    }
}
