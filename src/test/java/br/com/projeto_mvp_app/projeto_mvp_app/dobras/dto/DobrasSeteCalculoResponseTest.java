package br.com.projeto_mvp_app.projeto_mvp_app.dobras.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasSeteCalculoResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static br.com.projeto_mvp_app.projeto_mvp_app.dobras.mocks.DobrasMocks.umaDobraAtual;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getDuasCasas;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class DobrasSeteCalculoResponseTest {

    @Test
    public void of_deveConverterDeEntidadeParaResponseTresDobras_quandoExistiremTodosOsCampos() {
        var response = DobrasSeteCalculoResponse.of(umaDobraAtual());
        assertThat(response).isNotNull();
        assertThat(getDuasCasas(response.getDensidadeCorporal())).isEqualTo(BigDecimal.valueOf(1.29));
        assertThat(getDuasCasas(response.getPercentualGordura())).isEqualTo(BigDecimal.valueOf(-66.28));
    }

    @Test
    public void of_deveLancarException_quandoAlgumCampoNaoExistir() {
        var dobraAtual = umaDobraAtual();
        dobraAtual.setAxila(null);
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> DobrasSeteCalculoResponse.of(dobraAtual))
            .withMessage("É necessário que todas as dobras sejam preenchidas para realizar o cálculo das 7 dobras.");
    }
}
