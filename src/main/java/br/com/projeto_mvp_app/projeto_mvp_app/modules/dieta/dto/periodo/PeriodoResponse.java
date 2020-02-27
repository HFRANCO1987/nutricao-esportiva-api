package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoResponse {

    private Integer id;
    private String descricao;
    private LocalTime hora;

    public static PeriodoResponse of(Periodo periodo) {
        return PeriodoResponse
            .builder()
            .id(periodo.getId())
            .descricao(periodo.getDescricao())
            .hora(periodo.getHora())
            .build();
    }
}
