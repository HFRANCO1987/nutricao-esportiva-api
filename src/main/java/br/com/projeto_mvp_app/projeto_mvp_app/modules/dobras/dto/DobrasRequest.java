package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DobrasRequest {

    private Double peito;
    private Double axila;
    private Double tricep;
    private Double subescapular;
    private Double abdominal;
    private Double suprailiaca;
    private Double coxa;
}
