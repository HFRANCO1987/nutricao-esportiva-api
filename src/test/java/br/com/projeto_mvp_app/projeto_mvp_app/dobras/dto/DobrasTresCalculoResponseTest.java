package br.com.projeto_mvp_app.projeto_mvp_app.dobras.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasTresCalculoResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static br.com.projeto_mvp_app.projeto_mvp_app.dobras.mocks.DobrasMocks.umaDobraAtual;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getDuasCasas;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class DobrasTresCalculoResponseTest {

    @Test
    public void of_deveConverterDeEntidadeParaResponseTresDobras_quandoExistiremTodosOsCampos() {
        var response = DobrasTresCalculoResponse.of(umaDobraAtual());
        assertThat(response).isNotNull();
        assertThat(getDuasCasas(response.getDensidadeCorporal())).isEqualTo(BigDecimal.valueOf(1.03));
        assertThat(getDuasCasas(response.getPercentualGordura())).isEqualTo(BigDecimal.valueOf(30.58));
    }
}
