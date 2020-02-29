package br.com.projeto_mvp_app.projeto_mvp_app.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.CaloriaResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getUmaCasa;
import static org.assertj.core.api.Assertions.assertThat;

public class CaloriaResponseTest {

    @Test
    public void of_deveConverterDadosCalorias_quandoDadosEstiveremCorretos() {
        var response = CaloriaResponse.of(90.5);
        assertThat(response).isNotNull();
        assertThat(getUmaCasa(response.getEmagrecerMinimo()))
            .isEqualTo(getUmaCasa(1810.0));
        assertThat(getUmaCasa(response.getEmagrecerMaximo()))
            .isEqualTo(getUmaCasa(2253.5));
        assertThat(getUmaCasa(response.getEmagrecerMedia()))
            .isEqualTo(getUmaCasa(2936.7));
        assertThat(getUmaCasa(response.getManterMinimo()))
            .isEqualTo(BigDecimal.valueOf(2262.5));
        assertThat(getUmaCasa(response.getManterMaximo()))
            .isEqualTo(getUmaCasa(2706.0));
        assertThat(getUmaCasa(response.getManterMedia()))
            .isEqualTo(getUmaCasa(3615.5));
        assertThat(getUmaCasa(response.getEngordarMinimo()))
            .isEqualTo(getUmaCasa(2715.0));
        assertThat(getUmaCasa(response.getEngordarMaximo()))
            .isEqualTo(getUmaCasa(3167.5));
        assertThat(getUmaCasa(response.getEngordarMedia()))
            .isEqualTo(getUmaCasa(2941.3));
    }
}
