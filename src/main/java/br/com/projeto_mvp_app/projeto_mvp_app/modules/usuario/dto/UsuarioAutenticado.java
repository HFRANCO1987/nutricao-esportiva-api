package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioAutenticado {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private EPermissao permissao;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime ultimoAcesso;

    public boolean isAdmin() {
        return permissao.equals(ADMIN);
    }

    public boolean isUser() {
        return permissao.equals(USER);
    }

    public static UsuarioAutenticado of(Usuario usuario) {
        var usuarioAutenticado = new UsuarioAutenticado();
        BeanUtils.copyProperties(usuario, usuarioAutenticado);
        usuarioAutenticado.setPermissao(usuario.getPermissao().getPermissao());
        usuarioAutenticado.setDescricao(usuario.getPermissao().getDescricao());
        return usuarioAutenticado;
    }
}
