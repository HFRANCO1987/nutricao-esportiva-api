package br.com.projeto_mvp_app.projeto_mvp_app.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.AnalisePesoAlturaResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.PesoAlturaMocks.umPesoAlturaAnalise;
import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.PesoAlturaMocks.umPesoAlturaAtual;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalisePesoAlturaResponseTest {

    @Test
    public void of_deveCriarResponseAnalisePesoAltura_quandoDadosEstiveremCorretos() {
        var pesoAlturaProximo = umPesoAlturaAtual();
        pesoAlturaProximo.setPeso(90.3);
        var analise = AnalisePesoAlturaResponse.of(umPesoAlturaAnalise(), pesoAlturaProximo, false);
        assertThat(analise).isNotNull();
        assertThat(analise.getDiaSemana()).isEqualTo("Terça-feira");
        assertThat(analise.getPeso()).isEqualTo(94.5);
        assertThat(analise.getRelacaoPeso().setScale(1, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(4.2).setScale(1, RoundingMode.HALF_UP));
    }
}
