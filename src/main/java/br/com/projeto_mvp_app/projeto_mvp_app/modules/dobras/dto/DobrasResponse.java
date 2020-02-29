package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DobrasResponse {

    private DobrasTresCalculoResponse tresDobras;
    private DobrasSeteCalculoResponse seteDobras;

    public static DobrasResponse of(Dobras dobras, boolean tresDobras,  boolean seteDobras) {
        return DobrasResponse
            .builder()
            .tresDobras(tresDobras ? DobrasTresCalculoResponse.of(dobras) : null)
            .seteDobras(seteDobras ? DobrasSeteCalculoResponse.of(dobras) : null)
            .build();
    }
}
