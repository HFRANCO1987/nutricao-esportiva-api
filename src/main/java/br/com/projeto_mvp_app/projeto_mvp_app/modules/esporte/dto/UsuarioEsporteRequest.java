package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEsporteRequest {

    private Integer usuarioId;
    private Integer esporteId;
}
