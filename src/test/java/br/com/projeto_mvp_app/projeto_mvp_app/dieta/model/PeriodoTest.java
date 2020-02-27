package br.com.projeto_mvp_app.projeto_mvp_app.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import org.junit.Test;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMocks.umPeriodoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class PeriodoTest {

    @Test
    public void of_deveConverterEntidadeParaResponse_quandoPassarUmPeriodo() {
        var periodo = Periodo.of(umPeriodoRequest());
        assertThat(periodo).isNotNull();
        assertThat(periodo.getId()).isEqualTo(5);
        assertThat(periodo.getDescricao()).isEqualTo("Treino");
    }
}
