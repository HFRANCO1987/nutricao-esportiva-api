package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodosAlimentosResponse {

    private Integer id;
    private String descricao;
    private List<AlimentoResponse> alimentos;

    public static PeriodosAlimentosResponse of(PeriodoResponse periodo, List<AlimentoResponse> alimentos) {
        return PeriodosAlimentosResponse
            .builder()
            .id(periodo.getId())
            .descricao(periodo.getDescricao())
            .alimentos(alimentos)
            .build();
    }
}
