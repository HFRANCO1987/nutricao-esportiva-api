package br.com.projeto_mvp_app.projeto_mvp_app.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAnalisePesoResponse;
import org.junit.Test;

import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.mocks.UsuarioMocks.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioAnalisePesoResponseTest {

    @Test
    public void of_deveCriarResponseAnalisePesoComMensagemManteve_quandoPassadoTodosParametros() {
        var response = UsuarioAnalisePesoResponse.of(umUsuario(), umPesoAlturaAnalise(), umPesoAlturaAnaliseAnterior(),
            List.of(umaAnalisePesoAlturaResponse()));
        assertThat(response).isNotNull();
        assertThat(response.getMensagem()).isEqualTo("Olá, Victor Hugo Negrisoli, você manteve seu peso atual de 94.5kg");
        assertThat(response.getDiferencaPeriodo()).isEqualTo("8 dias");
    }

    @Test
    public void of_deveCriarResponseAnalisePesoComMensagemDiferencaPeso_quandoPassadoTodosParametros() {
        var anterior = umPesoAlturaAnaliseAnterior();
        anterior.setPeso(78.3);
        var response = UsuarioAnalisePesoResponse.of(umUsuario(), umPesoAlturaAnalise(), anterior,
            List.of(umaAnalisePesoAlturaResponse()));
        assertThat(response).isNotNull();
        assertThat(response.getMensagem()).isEqualTo("Olá, Victor Hugo Negrisoli, você aumentou 16.20kg (17.14%) em 8 dias");
        assertThat(response.getDiferencaPeriodo()).isEqualTo("8 dias");
    }
}
