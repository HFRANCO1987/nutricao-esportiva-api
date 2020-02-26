package br.com.projeto_mvp_app.projeto_mvp_app.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioResponse;
import org.junit.Test;

import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.mocks.UsuarioMocks.umUsuario;
import static br.com.projeto_mvp_app.projeto_mvp_app.mocks.UsuarioMocks.umaPermissaoAdmin;
import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioResponseTest {

    @Test
    public void of_deveRetornarResponse_quandoPassarEntidade() {
        var response = UsuarioResponse.of(umUsuario());
        assertThat(response).isNotNull();
        assertThat(response.getCpf()).isEqualTo("103.324.589-54");
        assertThat(response.getEmail()).isEqualTo("victorhugonegrisoli.ccs@gmail.com");
        assertThat(response.getNome()).isEqualTo("Victor Hugo Negrisoli");
        assertThat(response.getPermissoes()).isEqualTo(List.of(umaPermissaoAdmin()));
    }
}
