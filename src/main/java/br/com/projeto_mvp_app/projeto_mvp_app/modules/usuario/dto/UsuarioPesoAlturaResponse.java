package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPesoAlturaResponse {

    private Usuario usuario;
    List<PesoAltura> pesoAlturaHistorico;

    public static UsuarioPesoAlturaResponse of(Usuario usuario, List<PesoAltura> pesoAlturaHistorico) {
        return UsuarioPesoAlturaResponse
            .builder()
            .usuario(usuario)
            .pesoAlturaHistorico(pesoAlturaHistorico)
            .build();
    }
}
