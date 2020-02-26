package br.com.projeto_mvp_app.projeto_mvp_app.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.PesoAlturaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.getPage;
import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuarioAutenticado;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(UsuarioService.class)
@Sql(scripts = "classpath:/usuarios-test.sql")
@DataJpaTest
public class UsuarioServiceIntegrationTest {

    private static final Integer NUMERO_CASAS_DECIMAIS = 1;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PesoAlturaRepository pesoAlturaRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AutenticacaoService autenticacaoService;

    @Test
    public void getUsuarios_deveRetornarUsuarios_quandoPassarPaginacaoSemFiltros() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        var usuarios = usuarioService.getUsuarios(getPage(), new UsuarioFiltros()).getContent();
        assertThat(usuarios).isNotNull();
        assertThat(usuarios.size()).isEqualTo(8);
    }

    @Test
    public void getUsuarios_deveRetornarUsuarios_quandoPassarPaginacaoComFiltroDeNome() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        var usuarios = usuarioService
            .getUsuarios(getPage(), UsuarioFiltros.builder().nome("Negrisoli").build()).getContent();
        assertThat(usuarios).isNotNull();
        assertThat(usuarios.size()).isEqualTo(1);
        assertThat(usuarios.get(0).getId()).isEqualTo(7);
        assertThat(usuarios.get(0).getNome()).isEqualTo("Victor Hugo Negrisoli");
        assertThat(usuarios.get(0).getEmail()).isEqualTo("victorhugonegrisoli.ccs@gmail.com");
        assertThat(usuarios.get(0).getPermissoes().get(0).getDescricao()).isEqualTo("Administrador");
    }

    @Test
    public void getUsuarios_deveRetornarUsuarios_quandoPassarPaginacaoComFiltroDePermissao() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        assertThat(usuarioService
            .getUsuarios(getPage(), UsuarioFiltros.builder().permissao(EPermissao.USER).build()).getContent())
            .hasSize(3)
            .extracting("id", "nome")
            .containsExactlyInAnyOrder(
                tuple(59, "João Cadastro Altura Peso"),
                tuple(2, "User 2"),
                tuple(15, "Rafael Nonino Filho")
            );
    }

    @Test
    public void getUsuarioAutenticadoAtualizaUltimaData_deveAtualizarUltimaData_aoRecuperarUsuarioAutenticado() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        var ultimoAcessoAntigo = usuarioRepository.findById(7).get().getUltimoAcesso();
        var ultimoAcessoNovo = usuarioService.getUsuarioAutenticadoAtualizaUltimaData().getUltimoAcesso();
        assertThat(ultimoAcessoAntigo).isNotEqualTo(ultimoAcessoNovo);
        assertThat(ultimoAcessoNovo.toLocalDate()).isEqualTo(LocalDate.now());
    }

    @Test
    public void consultarAnalisePesoAltura_deveRetornarAnalisePesoAltura_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());

        var response = usuarioService.consultarAnalisePesoAltura();

        assertThat(response).isNotNull();
        assertThat(response.getPesoAtual()).isEqualTo(90.0);
        assertThat(response.getDataPesoAtual()).isEqualTo(LocalDate.parse("2020-02-20"));
        assertThat(response.getAnalisePesoAltura().size()).isEqualTo(4);
        assertThat(response.getAnalisePesoAltura().get(0).getDiaSemana()).isEqualTo("Quinta-feira");
        assertThat(response.getAnalisePesoAltura().get(0).getPeso()).isEqualTo(90.0);
        assertThat(response.getAnalisePesoAltura().get(0).getRelacaoPeso()
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(-4.0));
        assertThat(response.getAnalisePesoAltura().get(1).getDiaSemana()).isEqualTo("Sábado");
        assertThat(response.getAnalisePesoAltura().get(1).getPeso()).isEqualTo(94.0);
        assertThat(response.getAnalisePesoAltura().get(1).getRelacaoPeso()
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(86.8));
        assertThat(response.getAnalisePesoAltura().get(2).getDiaSemana()).isEqualTo("Segunda-feira");
        assertThat(response.getAnalisePesoAltura().get(2).getPeso()).isEqualTo(7.2);
        assertThat(response.getAnalisePesoAltura().get(2).getRelacaoPeso()
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(0.0));
        assertThat(response.getAnalisePesoAltura().get(3).getDiaSemana()).isEqualTo("Segunda-feira");
        assertThat(response.getAnalisePesoAltura().get(3).getPeso()).isEqualTo(7.2);
        assertThat(response.getAnalisePesoAltura().get(3).getRelacaoPeso()
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(0.0));
    }
}
