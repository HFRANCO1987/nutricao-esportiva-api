package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoAlimentoDietaRequest {

    private Integer dietaId;
    List<PeriodoAlimentoRequestList> periodosAlimentos;
}
