package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.DietaCompletaResponse;
import org.junit.Test;

import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.umPeriodosAlimentosResponse;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.umaDieta;
import static org.assertj.core.api.Assertions.assertThat;

public class DietaCompletaResponseTest {

    @Test
    public void of_deveConverterParaResponse_quandoReceberEntidadeComListaDePeriodosAlimentosResponse() {
        var response = DietaCompletaResponse.of(umaDieta(), List.of(umPeriodosAlimentosResponse()));
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getDescricao()).isEqualTo("Teste");
        assertThat(response.getPeriodosAlimentos().get(0).getId()).isEqualTo(1);
        assertThat(response.getPeriodosAlimentos().get(0).getDescricao()).isEqualTo("Arroz");
        assertThat(response.getPeriodosAlimentos().get(0).getAlimentos().get(0).getDescricao()).isEqualTo("Arroz");
        assertThat(response.getPeriodosAlimentos().get(0).getAlimentos().get(0).getCategoria()).isEqualTo("Cereais");
    }
}
