package br.com.projeto_mvp_app.projeto_mvp_app.dobras.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasResponse;
import org.junit.Test;

import static br.com.projeto_mvp_app.projeto_mvp_app.dobras.mocks.DobrasMocks.umaDobraAtual;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class DobrasResponseTest {

    @Test
    public void of_deveConverterDeEntidadeParaResponseTresSete_quandoExistiremTodosOsCamposComParametrosTrue() {
        var response = DobrasResponse.of(umaDobraAtual(), true, true);
        assertThat(response).isNotNull();
        assertThat(response.getTresDobras()).isNotNull();
        assertThat(response.getSeteDobras()).isNotNull();
    }

    @Test
    public void of_deveConverterDeEntidadeParaResponseTres_quandoExistiremTodosOsCamposComTresTrueSeteFalse() {
        var response = DobrasResponse.of(umaDobraAtual(), true, false);
        assertThat(response).isNotNull();
        assertThat(response.getTresDobras()).isNotNull();
        assertThat(response.getSeteDobras()).isNull();
    }

    @Test
    public void of_deveConverterDeEntidadeParaResponseSete_quandoExistiremTodosOsCamposComTresFalseSeteTrue() {
        var response = DobrasResponse.of(umaDobraAtual(), false, true);
        assertThat(response).isNotNull();
        assertThat(response.getTresDobras()).isNull();
        assertThat(response.getSeteDobras()).isNotNull();
    }
}
