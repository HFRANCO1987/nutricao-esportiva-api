package br.com.projeto_mvp_app.projeto_mvp_app.dobras.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
import org.junit.Test;

import static br.com.projeto_mvp_app.projeto_mvp_app.dobras.mocks.DobrasMocks.umaDobraRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class DobrasTest {

    @Test
    public void of_deveConverterParaEntidade_quandoReceberUmRequest() {
        var dobras = Dobras.of(umaDobraRequest(), 1);
        assertThat(dobras).isNotNull();
        assertThat(dobras.getUsuario().getId()).isEqualTo(1);
        assertThat(dobras.getPeito()).isEqualTo(146.4);
        assertThat(dobras.getAxila()).isEqualTo(156.4);
        assertThat(dobras.getTricep()).isEqualTo(200.2);
        assertThat(dobras.getSubescapular()).isEqualTo(132.4);
        assertThat(dobras.getAbdominal()).isEqualTo(132.4);
        assertThat(dobras.getSuprailiaca()).isEqualTo(199.1);
        assertThat(dobras.getCoxa()).isEqualTo(123.1);
    }
}
