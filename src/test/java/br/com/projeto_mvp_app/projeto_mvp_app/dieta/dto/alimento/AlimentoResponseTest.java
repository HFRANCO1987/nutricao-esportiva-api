package br.com.projeto_mvp_app.projeto_mvp_app.dieta.dto.alimento;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.AlimentoMocks.umAlimento;
import static org.assertj.core.api.Assertions.assertThat;

public class AlimentoResponseTest {

    @Test
    public void of_deveConverterParaResponse_quandoReceberEntidadeAlimento() {
        var response = AlimentoResponse.of(156.2, umAlimento());
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getDescricao()).isEqualTo("Arroz");
        assertThat(response.getCategoria()).isEqualTo("Cereais");
        assertThat(response.getImagem()).isEqualTo(null);
        assertThat(response.getQuantidade().setScale(2, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(156.2).setScale(2, RoundingMode.HALF_UP));
        assertThat(response.getInformacoes().getCalcio_mg()).isEqualTo(15.5);
        assertThat(response.getInformacoes().getCarboidrato_g()).isEqualTo(165.0);
        assertThat(response.getInformacoes().getCinzas_g()).isEqualTo(15.3);
        assertThat(response.getInformacoes().getColesterol_mg()).isEqualTo(12.46);
        assertThat(response.getInformacoes().getEnergia_kj()).isEqualTo(56.3);
        assertThat(response.getInformacoes().getCobre_mg()).isEqualTo(13.2);
        assertThat(response.getInformacoes().getEnergia_kcal()).isEqualTo(123.3);
        assertThat(response.getInformacoes().getFerro_mg()).isEqualTo(12.2);
        assertThat(response.getInformacoes().getFibra_alimentar_g()).isEqualTo(15.3);
        assertThat(response.getInformacoes().getManganes_mg()).isEqualTo(12.46);
        assertThat(response.getInformacoes().getFosforo_mg()).isEqualTo(56.3);
        assertThat(response.getInformacoes().getPotassio_mg()).isEqualTo(13.2);
        assertThat(response.getInformacoes().getProtei_g()).isEqualTo(123.3);
        assertThat(response.getInformacoes().getRiboflavi_mg()).isEqualTo(12.2);
    }
}
