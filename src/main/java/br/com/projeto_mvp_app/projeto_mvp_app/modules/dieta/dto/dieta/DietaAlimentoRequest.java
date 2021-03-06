package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietaAlimentoRequest {

    private Integer dietaId;
    private Integer alimentoId;
    private Integer periodoId;
    private Double quantidade;
}
