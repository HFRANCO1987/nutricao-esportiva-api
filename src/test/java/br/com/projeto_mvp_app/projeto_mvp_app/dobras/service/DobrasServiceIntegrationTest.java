package br.com.projeto_mvp_app.projeto_mvp_app.dobras.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.repository.DobrasRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.service.DobrasService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getDuasCasas;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(DobrasService.class)
@Sql(scripts = {"classpath:/usuarios-test.sql", "classpath:/dobras-test.sql"})
@DataJpaTest
public class DobrasServiceIntegrationTest {

    @Autowired
    private DobrasService dobrasService;
    @Autowired
    private DobrasRepository dobrasRepository;
    @MockBean
    private AutenticacaoService autenticacaoService;

    @Before
    public void setup() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
    }

    @Test
    public void retornarCalculoDobras_deveRetornar3DobrasCalculadas_quandoInformarTrue3Dobras() {
        var tresDobras = dobrasService.retornarCalculoDobras(true, false);
        assertThat(tresDobras).isNotNull();
        assertThat(tresDobras.getTresDobras()).isNotNull();
        assertThat(tresDobras.getSeteDobras()).isNull();
        assertThat(getDuasCasas(tresDobras.getTresDobras().getDensidadeCorporal()))
            .isEqualTo(BigDecimal.valueOf(1.04));
        assertThat(getDuasCasas(tresDobras.getTresDobras().getPercentualGordura()))
            .isEqualTo(BigDecimal.valueOf(25.96));
    }

    @Test
    public void retornarCalculoDobras_deveRetornar7DobrasCalculadas_quandoInformarTrue7Dobras() {
        var seteDobras = dobrasService.retornarCalculoDobras(false, true);
        assertThat(seteDobras).isNotNull();
        assertThat(seteDobras.getTresDobras()).isNull();
        assertThat(seteDobras.getSeteDobras()).isNotNull();
        assertThat(getDuasCasas(seteDobras.getSeteDobras().getDensidadeCorporal()))
            .isEqualTo(BigDecimal.valueOf(1.29));
        assertThat(getDuasCasas(seteDobras.getSeteDobras().getPercentualGordura()))
            .isEqualTo(BigDecimal.valueOf(-66.28));
    }

    @Test
    public void retornarCalculoDobras_deveRetornar3E7DobrasCalculadas_quandoInformarTrue3E7Dobras() {
        var dobras = dobrasService.retornarCalculoDobras(true, true);
        assertThat(dobras).isNotNull();
        assertThat(dobras.getTresDobras()).isNotNull();
        assertThat(dobras.getSeteDobras()).isNotNull();
        assertThat(getDuasCasas(dobras.getTresDobras().getDensidadeCorporal()))
            .isEqualTo(BigDecimal.valueOf(1.04));
        assertThat(getDuasCasas(dobras.getTresDobras().getPercentualGordura()))
            .isEqualTo(BigDecimal.valueOf(25.96));
        assertThat(getDuasCasas(dobras.getSeteDobras().getDensidadeCorporal()))
            .isEqualTo(BigDecimal.valueOf(1.29));
        assertThat(getDuasCasas(dobras.getSeteDobras().getPercentualGordura()))
            .isEqualTo(BigDecimal.valueOf(-66.28));
    }

    @Test
    public void retornarCalculoDobras_deveRetornarSemCalculos_quandoTodosOsParametrosForemFalso() {
        var semDobras = dobrasService.retornarCalculoDobras(false, false);
        assertThat(semDobras).isNotNull();
        assertThat(semDobras.getTresDobras()).isNull();
        assertThat(semDobras.getSeteDobras()).isNull();
    }
}
