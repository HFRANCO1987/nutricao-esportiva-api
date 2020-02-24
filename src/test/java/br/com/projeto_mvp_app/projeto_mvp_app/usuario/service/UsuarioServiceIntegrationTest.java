package br.com.projeto_mvp_app.projeto_mvp_app.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioFiltros;
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

import static br.com.projeto_mvp_app.projeto_mvp_app.mocks.UsuarioMocks.getPage;
import static br.com.projeto_mvp_app.projeto_mvp_app.mocks.UsuarioMocks.umUsuarioAutenticado;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(UsuarioService.class)
@Sql(scripts = "classpath:/create-test-database.sql")
@DataJpaTest
public class UsuarioServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AutenticacaoService autenticacaoService;
    @MockBean
    private PesoAlturaRepository pesoAlturaRepository;

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
}
