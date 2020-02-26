package br.com.projeto_mvp_app.projeto_mvp_app.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.TokenResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenResponseTest {

    @Test
    public void token_deveRetornarToken_quandoExistirBearer() {
        var token = "Bearer bca3a3c3-7726-4d5b-a25a-ecf8cdaa4f05";
        var accessToken = new TokenResponse(token);
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getBearerToken()).isEqualTo("Bearer bca3a3c3-7726-4d5b-a25a-ecf8cdaa4f05");
        assertThat(accessToken.getToken()).isEqualTo("bca3a3c3-7726-4d5b-a25a-ecf8cdaa4f05");
    }
}
