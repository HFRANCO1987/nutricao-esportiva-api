package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(PeriodoService.class)
@Sql(scripts = {"classpath:/usuarios-test.sql", "classpath:/alimentos-test.sql", "classpath:/dietas-test.sql"})
@DataJpaTest
public class PeriodoServiceIntegrationTest {

    @Autowired
    private PeriodoService periodoService;
    @MockBean
    private AutenticacaoService autenticacaoService;

    @Test
    public void buscarPeriodosDoUsuario_deveRetornarApenasPeriodosDoUsuario_quandoFlagForFalsaPorIdUsuario() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        assertThat(periodoService.buscarPeriodosDaDieta(116))
            .hasSize(4)
            .extracting("id", "descricao")
            .containsExactlyInAnyOrder(
                tuple(10, "Manhã"),
                tuple(11, "Almoço"),
                tuple(12, "Tarde"),
                tuple(13, "Noite")
            );
    }

    @Test
    public void adicionarPeriodosPadroes_deveVincular4PeriodosPadroes_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(3);

        assertThat(periodoService.buscarPeriodosDaDieta(116))
            .hasSize(4)
            .extracting("id", "descricao")
            .containsExactlyInAnyOrder(
                tuple(10, "Manhã"),
                tuple(11, "Almoço"),
                tuple(12, "Tarde"),
                tuple(13, "Noite")
            );
    }
}
