package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import lombok.Data;

@Data
public class DietaAlimentoRequest {

    private Integer dietaId;
    private Integer alimentoId;
    private Integer periodoId;
    private Double quantidade;
}
