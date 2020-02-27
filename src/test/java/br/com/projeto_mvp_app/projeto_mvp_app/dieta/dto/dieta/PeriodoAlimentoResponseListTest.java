package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.PeriodoAlimentoResponseList;
import org.junit.Test;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.umPeriodoAlimentoDieta;
import static org.assertj.core.api.Assertions.assertThat;

public class PeriodoAlimentoResponseListTest {

    @Test
    public void of_deveConverterParaResponse_quandoReceberPeriodoAlimento() {
        var response = PeriodoAlimentoResponseList.of(umPeriodoAlimentoDieta());
        assertThat(response).isNotNull();
        assertThat(response.getAlimento()).isEqualTo("Arroz");
        assertThat(response.getPeriodo()).isEqualTo("Pré-Treino");
        assertThat(response.getCategoria()).isEqualTo("Cereais");
    }
}
