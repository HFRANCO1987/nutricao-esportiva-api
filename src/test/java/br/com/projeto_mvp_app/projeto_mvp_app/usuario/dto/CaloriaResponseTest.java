package br.com.projeto_mvp_app.projeto_mvp_app.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.CaloriaResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class CaloriaResponseTest {

    @Test
    public void of_deveConverterDadosCalorias_quandoDadosEstiveremCorretos() {
        var response = CaloriaResponse.of(90.5);
        assertThat(response).isNotNull();
        assertThat(response.getEmagrecerMinimo().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(1810.0).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getEmagrecerMaximo().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2253.5).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getEmagrecerMedia().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2031.7).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getManterMinimo().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2262.5).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getManterMaximo().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2706.0).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getManterMedia().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2484.2).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getEngordarMinimo().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2715.0).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getEngordarMaximo().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(3167.5).setScale(1, RoundingMode.HALF_UP));
        assertThat(response.getEngordarMedia().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(2941.3).setScale(1, RoundingMode.HALF_UP));
    }
}
