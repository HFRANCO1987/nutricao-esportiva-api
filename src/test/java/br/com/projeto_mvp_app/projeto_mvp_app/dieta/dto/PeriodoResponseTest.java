package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import org.junit.Test;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMock.umPeriodoUsuario;
import static org.assertj.core.api.Assertions.assertThat;

public class PeriodoResponseTest {

    @Test
    public void of_deveConverterEntidadeParaResponse_quandoPassarUmPeriodo() {
        var response = PeriodoResponse.of(umPeriodoUsuario());
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(5);
        assertThat(response.getDescricao()).isEqualTo("Pré-Treino");
    }
}
