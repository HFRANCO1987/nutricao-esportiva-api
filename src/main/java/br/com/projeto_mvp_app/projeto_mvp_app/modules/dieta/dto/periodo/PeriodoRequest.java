package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoRequest {

    private Integer id;
    private String descricao;
}
