package br.com.projeto_mvp_app.projeto_mvp_app.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.ESexo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Permissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioMocks {

    public static UsuarioAutenticado umUsuarioAutenticado() {
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

    public static PageRequest getPage() {
        var page = new PageRequest();
        page.setPage(0);
        page.setLimit(10);
        page.setSize(10);
        page.setOrderDirection("ASC");
        page.setOrderBy("id");
        return page;
    }

    public static UsuarioRequest umUsuarioRequest() {
        return UsuarioRequest
            .builder()
            .id(1)
            .nome("Victor Hugo Negrisoli")
            .cpf("103.324.589-54")
            .email("victorhugonegrisoli.ccs@gmail.com")
            .altura(1.72)
            .peso(94.5)
            .dataNascimento(LocalDate.parse("1998-03-31"))
            .senha("123456")
            .sexo(ESexo.FEMININO)
            .build();
    }

    public static Usuario umUsuario() {
        return Usuario
            .builder()
            .id(1)
            .permissao(umaPermissao())
            .nome("Victor Hugo Negrisoli")
            .cpf("103.324.589-54")
            .email("victorhugonegrisoli.ccs@gmail.com")
            .dataCadastro(LocalDateTime.now())
            .dataNascimento(LocalDate.parse("1998-03-31"))
            .senha("123456")
            .sexo(ESexo.FEMININO)
            .ultimoAcesso(LocalDateTime.now())
            .build();
    }

    public static Permissao umaPermissao() {
        return Permissao
            .builder()
            .id(1)
            .descricao("ADMIN")
            .permissao(EPermissao.ADMIN)
            .build();
    }
}
