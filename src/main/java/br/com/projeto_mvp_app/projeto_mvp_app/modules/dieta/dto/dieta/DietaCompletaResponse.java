package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietaCompletaResponse {

    private Integer id;
    private String descricao;
    private List<PeriodosAlimentosResponse> periodosAlimentos;

    public static DietaCompletaResponse of(Dieta dieta, List<PeriodosAlimentosResponse> periodosAlimentos) {
        return DietaCompletaResponse
            .builder()
            .id(dieta.getId())
            .descricao(dieta.getDescricao())
            .periodosAlimentos(periodosAlimentos)
            .build();
    }
}
