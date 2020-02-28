package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoRequest {

    private Integer id;
    private Integer dietaId;
    private String descricao;
    private LocalTime hora;
}
