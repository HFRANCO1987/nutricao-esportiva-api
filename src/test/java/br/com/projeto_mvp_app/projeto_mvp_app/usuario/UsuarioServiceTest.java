package br.com.projeto_mvp_app.projeto_mvp_app.usuario;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(UsuarioService.class)
@Sql(scripts = "classpath:/create-test-database.sql")
@DataJpaTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;
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
        assertThat(usuarios.get(0).getPermissao().getDescricao()).isEqualTo("Administrador");
    }

    private UsuarioAutenticado umUsuarioAutenticado() {
        return UsuarioAutenticado
            .builder()
            .id(7)
            .nome("Victor Hugo Negrisoli")
            .email("victorhugonegrisoli.ccs@gmail.com")
            .cpf("103.324.589-54")
            .descricao("Administrador")
            .permissao(EPermissao.ADMIN)
            .ultimoAcesso(LocalDateTime.now())
            .build();
    }

    private PageRequest getPage() {
        var page = new PageRequest();
        page.setPage(0);
        page.setLimit(10);
        page.setSize(10);
        page.setOrderDirection("ASC");
        page.setOrderBy("id");
        return page;
    }
}
