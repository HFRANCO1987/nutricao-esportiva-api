package br.com.projeto_mvp_app.projeto_mvp_app.esporte.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.service.EsporteService;
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
@Import(EsporteService.class)
@Sql(scripts = {"classpath:/usuarios-test.sql", "classpath:/esportes-test.sql"})
@DataJpaTest
public class EsporteServiceIntegrationTest {

    @Autowired
    private EsporteService esporteService;
    @MockBean
    private AutenticacaoService autenticacaoService;

    @Test
    public void buscarTodos_deveRetornarTodos_quandoSolicitado() {
        assertThat(esporteService.buscarTodos())
            .hasSize(55)
            .extracting("id", "atividade")
            .containsExactlyInAnyOrder(
                tuple(1, "Andando de bicicleta"),
                tuple(5, "Beijar e fazer carícias"),
                tuple(8, "Caminhando rápido"),
                tuple(9, "Caminhar devagar"),
                tuple(10, "Carregando bebê"),
                tuple(13, "Compra no Supermercado"),
                tuple(2, "Balé"),
                tuple(15, "Corrida"),
                tuple(16, "Cozinhar"),
                tuple(18, "Dançando rápido"),
                tuple(19, "Deitado"),
                tuple(20, "Digitando"),
                tuple(21, "Dormindo"),
                tuple(22, "Escrever"),
                tuple(25, "Estudar"),
                tuple(26, "Exercício leve"),
                tuple(27, "Falando ao telefone"),
                tuple(28, "Fazer amor"),
                tuple(29, "Ficar de pé"),
                tuple(37, "Jogando vídeo game"),
                tuple(38, "Jogar futebol"),
                tuple(40, "Lavar louça"),
                tuple(41, "Limpeza de casa"),
                tuple(45, "Natação"),
                tuple(46, "Pintar casa"),
                tuple(49, "Subir escada"),
                tuple(52, "Trabalhar leve em pé"),
                tuple(53, "Trabalho mental casa"),
                tuple(3, "Basquete"),
                tuple(4, "Beijando"),
                tuple(6, "Boxe"),
                tuple(7, "Caminhada"),
                tuple(11, "Capoeira"),
                tuple(12, "Ciclismo"),
                tuple(14, "Corrida"),
                tuple(17, "Dança de Salão"),
                tuple(23, "Esqui aquático"),
                tuple(24, "Esqui na neve"),
                tuple(30, "Futebol"),
                tuple(31, "Ginástica aeróbica"),
                tuple(32, "Ginástica olímpica"),
                tuple(33, "Golfe"),
                tuple(34, "Handebol"),
                tuple(35, "Hidroginástica"),
                tuple(36, "Jiu-jitsu"),
                tuple(39, "Judô"),
                tuple(42, "Mountain bike"),
                tuple(43, "Musculação"),
                tuple(44, "Natação"),
                tuple(47, "Remo"),
                tuple(48, "Squash"),
                tuple(50, "Surfe"),
                tuple(51, "Tênis"),
                tuple(54, "Vôlei"),
                tuple(55, "Windsurf")
            );
    }

    @Test
    public void buscarTodosEsportesDoUsuario_deveRetornarTodos_quandoSolicitado() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        assertThat(esporteService.buscarTodosEsportesDoUsuario())
            .hasSize(4)
            .extracting("id", "atividade")
            .containsExactlyInAnyOrder(
                tuple(1, "Andando de bicicleta"),
                tuple(2, "Balé"),
                tuple(3, "Basquete"),
                tuple(4, "Beijando")
            );
    }

    @Test
    public void buscarEsportePorId_deveRetornarTodos_quandoSolicitado() {
        var esporte = esporteService.buscarEsportePorId(1);
        assertThat(esporte.getId()).isEqualTo(1);
        assertThat(esporte.getAtividade()).isEqualTo("Andando de bicicleta");
        assertThat(esporte.getKcalHoraMax()).isEqualTo(300.00);
        assertThat(esporte.getKcalHoraMin()).isEqualTo(180.00);
        assertThat(esporte.getKcalMinMax()).isEqualTo(5.00);
        assertThat(esporte.getKcalMinMin()).isEqualTo(3.00);
    }
}
