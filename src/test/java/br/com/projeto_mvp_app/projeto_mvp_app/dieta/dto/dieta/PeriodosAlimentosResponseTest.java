package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.PeriodosAlimentosResponse;
import org.junit.Test;

import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.AlimentoMocks.umAlimentoResponse;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMocks.umPeriodoResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class PeriodosAlimentosResponseTest {

    @Test
    public void of_deveConverterParaResponse_quandoReceberPeriodoAlimento() {
        var response = PeriodosAlimentosResponse.of(umPeriodoResponse(), List.of(umAlimentoResponse()));
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getDescricao()).isEqualTo("Manhã");
        assertThat(response.getAlimentos().get(0).getDescricao()).isEqualTo("Arroz");
        assertThat(response.getAlimentos().get(0).getCategoria()).isEqualTo("Cereais");

    }
}
