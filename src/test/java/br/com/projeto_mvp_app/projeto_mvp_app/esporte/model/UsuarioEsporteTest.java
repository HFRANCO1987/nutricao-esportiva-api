package br.com.projeto_mvp_app.projeto_mvp_app.esporte.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.UsuarioEsporte;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioEsporteTest {

    @Test
    public void of_deveConverterParaModel_quandoInformarIdEsporteEIdUsuario() {
        var usuarioEsporte = UsuarioEsporte.of(7, 1);

        assertThat(usuarioEsporte).isNotNull();
        assertThat(usuarioEsporte.getUsuario().getId()).isEqualTo(7);
        assertThat(usuarioEsporte.getEsporte().getId()).isEqualTo(1);
    }
}
