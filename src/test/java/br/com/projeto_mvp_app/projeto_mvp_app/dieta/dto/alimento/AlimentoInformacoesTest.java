package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto.alimento;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoInformacoes;
import org.junit.Test;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.AlimentoMocks.umAlimento;
import static org.assertj.core.api.Assertions.assertThat;

public class AlimentoInformacoesTest {

    @Test
    public void of_deveConverterParaResponse_quandoReceberEntidadeAlimento() {
        var response = AlimentoInformacoes.of(umAlimento());
        assertThat(response).isNotNull();
        assertThat(response.getCalcio_mg()).isEqualTo(15.5);
        assertThat(response.getCarboidrato_g()).isEqualTo(165.0);
        assertThat(response.getCinzas_g()).isEqualTo(15.3);
        assertThat(response.getColesterol_mg()).isEqualTo(12.46);
        assertThat(response.getEnergia_kj()).isEqualTo(56.3);
        assertThat(response.getCobre_mg()).isEqualTo(13.2);
        assertThat(response.getEnergia_kcal()).isEqualTo(123.3);
        assertThat(response.getFerro_mg()).isEqualTo(12.2);
        assertThat(response.getFibra_alimentar_g()).isEqualTo(15.3);
        assertThat(response.getManganes_mg()).isEqualTo(12.46);
        assertThat(response.getFosforo_mg()).isEqualTo(56.3);
        assertThat(response.getPotassio_mg()).isEqualTo(13.2);
        assertThat(response.getProtei_g()).isEqualTo(123.3);
        assertThat(response.getRiboflavi_mg()).isEqualTo(12.2);
    }
}
