package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
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

    public static PeriodosAlimentosResponse of(Periodo periodo, List<AlimentoResponse> alimentos) {
        return PeriodosAlimentosResponse
            .builder()
            .id(periodo.getId())
            .descricao(periodo.getDescricao())
            .alimentos(alimentos)
            .build();
    }
}
