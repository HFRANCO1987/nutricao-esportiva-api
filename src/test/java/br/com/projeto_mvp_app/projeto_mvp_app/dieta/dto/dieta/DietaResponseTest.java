package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.DietaResponse;
import org.junit.Test;

import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.umPeriodoAlimentoDieta;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.umaDieta;
import static org.assertj.core.api.Assertions.assertThat;

public class DietaResponseTest {

    @Test
    public void of_deveConverterParaResponse_quandoReceberEntidadeComListaDePeriodosAlimentos() {
        var response = DietaResponse.of(umaDieta(), List.of(umPeriodoAlimentoDieta()));
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getDescricao()).isEqualTo("Teste");;
        assertThat(response.getAlimentosDaDieta().get(0).getAlimento()).isEqualTo("Arroz");
        assertThat(response.getAlimentosDaDieta().get(0).getCategoria()).isEqualTo("Cereais");
        assertThat(response.getAlimentosDaDieta().get(0).getPeriodo()).isEqualTo("Pré-Treino");
    }
}
