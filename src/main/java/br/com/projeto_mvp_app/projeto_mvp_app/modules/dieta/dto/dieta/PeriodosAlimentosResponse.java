package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodosAlimentosResponse {

    private Integer id;
    private String descricao;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;
    private List<AlimentoResponse> alimentos;

    public static PeriodosAlimentosResponse of(PeriodoResponse periodo, List<AlimentoResponse> alimentos) {
        return PeriodosAlimentosResponse
            .builder()
            .id(periodo.getId())
            .descricao(periodo.getDescricao())
            .hora(periodo.getHora())
            .alimentos(alimentos)
            .build();
    }
}
