package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.DietaFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.DietaService;
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

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.getPage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import({DietaService.class, PeriodoService.class})
@Sql(scripts = {"classpath:/usuarios-test.sql", "classpath:/alimentos-test.sql", "classpath:/dietas-test.sql"})
@DataJpaTest
public class DietaServiceIntegrationTest {

    @Autowired
    private DietaService dietaService;
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private PeriodoAlimentoDietaRepository periodoAlimentoDietaRepository;
    @Autowired
    private PeriodoService periodoService;
    @MockBean
    private AutenticacaoService autenticacaoService;

    @Test
    public void buscarDietaComDadosCompletos_deveRetornarDietaCompleta_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var response = dietaService.buscarDietaComDadosCompletos(120);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(120);
        assertThat(response.getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.getAlimentosDaDieta().size()).isEqualTo(4);
        assertThat(response.getAlimentosDaDieta().get(0).getAlimento()).isEqualTo("Arroz, integral, cozido");
        assertThat(response.getAlimentosDaDieta().get(0).getCategoria()).isEqualTo("Cereais e derivados");
        assertThat(response.getAlimentosDaDieta().get(0).getPeriodo()).isEqualTo("Manhã");
        assertThat(response.getAlimentosDaDieta().get(1).getAlimento()).isEqualTo("Abacaxi, polpa, congelada");
        assertThat(response.getAlimentosDaDieta().get(1).getCategoria()).isEqualTo("Frutas e derivados");
        assertThat(response.getAlimentosDaDieta().get(1).getPeriodo()).isEqualTo("Noite");
        assertThat(response.getAlimentosDaDieta().get(2).getAlimento()).isEqualTo("Bolo, pronto, chocolate");
        assertThat(response.getAlimentosDaDieta().get(2).getCategoria()).isEqualTo("Cereais e derivados");
        assertThat(response.getAlimentosDaDieta().get(2).getPeriodo()).isEqualTo("Tarde");
        assertThat(response.getAlimentosDaDieta().get(3).getAlimento()).isEqualTo("Couve, manteiga, refogada");
        assertThat(response.getAlimentosDaDieta().get(3).getCategoria()).isEqualTo("Verduras, hortaliças e derivados");
        assertThat(response.getAlimentosDaDieta().get(3).getPeriodo()).isEqualTo("Tarde");
    }

    @Test
    public void buscarTodas_deveRetornarDietasPaginadas_quandoDadosEstiveremCorretosSemFiltros() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var response = dietaService.buscarTodas(getPage(), new DietaFiltros());

        assertThat(response.getContent()).isNotNull();
        assertThat(response.getContent().size()).isEqualTo(3);
        assertThat(response.getContent().get(0).getId()).isEqualTo(116);
        assertThat(response.getContent().get(0).getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.getContent().get(1).getId()).isEqualTo(120);
        assertThat(response.getContent().get(1).getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.getContent().get(2).getId()).isEqualTo(169);
        assertThat(response.getContent().get(2).getDescricao()).isEqualTo("Nova Dieta Completa Response");
    }

    @Test
    public void buscarTodas_deveRetornarDietasPaginadas_quandoDadosEstiveremCorretosComFiltroDePeriodo() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var filtros = new DietaFiltros();
        filtros.setDescricaoPeriodo("Manhã");

        var response = dietaService.buscarTodas(getPage(), filtros);

        assertThat(response.getContent()).isNotNull();
        assertThat(response.getContent().size()).isEqualTo(2);
        assertThat(response.getContent().get(0).getId()).isEqualTo(116);
        assertThat(response.getContent().get(0).getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.getContent().get(1).getId()).isEqualTo(120);
        assertThat(response.getContent().get(1).getDescricao()).isEqualTo("Atualizar dieta 2");
    }

    @Test
    public void buscarTodasSemPaginacao_deveRetornarDietasPaginadas_quandoDadosEstiveremCorretosSemFiltros() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var response = dietaService.buscarTodasSemPaginacao(new DietaFiltros());

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(3);
        assertThat(response.get(0).getId()).isEqualTo(116);
        assertThat(response.get(0).getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.get(1).getId()).isEqualTo(120);
        assertThat(response.get(1).getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.get(2).getId()).isEqualTo(169);
        assertThat(response.get(2).getDescricao()).isEqualTo("Nova Dieta Completa Response");
    }

    @Test
    public void buscarTodasSemPaginacao_deveRetornarDietasPaginadas_quandoDadosEstiveremCorretosComFiltroDePeriodo() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var filtros = new DietaFiltros();
        filtros.setDescricaoPeriodo("Manhã");

        var response = dietaService.buscarTodasSemPaginacao(filtros);

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getId()).isEqualTo(116);
        assertThat(response.get(0).getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.get(1).getId()).isEqualTo(120);
        assertThat(response.get(1).getDescricao()).isEqualTo("Atualizar dieta 2");
    }

    @Test
    public void buscarDietaCompleta_deveRetornarDietasCompleta_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var response = dietaService.buscarDietaCompleta(116);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(116);
        assertThat(response.getDescricao()).isEqualTo("Atualizar dieta 2");
        assertThat(response.getPeriodosAlimentos().size()).isEqualTo(4);
        assertThat(response.getPeriodosAlimentos().get(0).getDescricao()).isEqualTo("Manhã");
        assertThat(response.getPeriodosAlimentos().get(0).getAlimentos().size()).isEqualTo(2);
        assertThat(response.getPeriodosAlimentos().get(1).getDescricao()).isEqualTo("Almoço");
        assertThat(response.getPeriodosAlimentos().get(1).getAlimentos().size()).isEqualTo(1);
        assertThat(response.getPeriodosAlimentos().get(2).getDescricao()).isEqualTo("Tarde");
        assertThat(response.getPeriodosAlimentos().get(2).getAlimentos().size()).isEqualTo(1);
        assertThat(response.getPeriodosAlimentos().get(3).getDescricao()).isEqualTo("Noite");
        assertThat(response.getPeriodosAlimentos().get(3).getAlimentos().size()).isEqualTo(0);
    }

    @Test
    public void buscarDietaAtualCompleta_deveRetornarDietasCompleta_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var response = dietaService.buscarDietaAtualCompleta();

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(169);
        assertThat(response.getDescricao()).isEqualTo("Nova Dieta Completa Response");
        assertThat(response.getPeriodosAlimentos().size()).isEqualTo(2);
        assertThat(response.getPeriodosAlimentos().get(0).getDescricao()).isEqualTo("Pré-Treino");
        assertThat(response.getPeriodosAlimentos().get(0).getAlimentos().get(0).getDescricao())
            .isEqualTo("Cereais, mistura para vitami, trigo, cevada e aveia");
        assertThat(response.getPeriodosAlimentos().get(0).getAlimentos().get(0).getCategoria())
            .isEqualTo("Cereais e derivados");
        assertThat(response.getPeriodosAlimentos().get(1).getDescricao()).isEqualTo("Pós-Treino");
        assertThat(response.getPeriodosAlimentos().get(1).getAlimentos().size()).isEqualTo(0);
    }
}