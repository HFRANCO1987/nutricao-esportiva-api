package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import lombok.Data;

@Data
public class DietaAlimentoRequest {

    private Integer dietaId;
    private Integer alimentoId;
    private Integer periodoId;

}
