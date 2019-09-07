package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
import lombok.Data;

import java.time.LocalDateTime;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.*;

@Data
public class UsuarioAutenticado {

    private Integer id;
    private String nome;
    private String email;
    private EPermissao permissao;
    private String descricao;
    private LocalDateTime ultimoAcesso;

    public boolean isAdmin() {
        return permissao.equals(ADMIN);
    }

    public boolean isUser() {
        return permissao.equals(USER);
    }
}
