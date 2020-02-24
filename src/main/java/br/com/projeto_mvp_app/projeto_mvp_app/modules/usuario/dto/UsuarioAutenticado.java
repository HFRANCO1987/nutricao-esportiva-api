package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Permissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.ADMIN;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.USER;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioAutenticado {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private List<EPermissao> permissoes;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime ultimoAcesso;

    public boolean isAdmin() {
        return permissoes.contains(ADMIN);
    }

    public boolean isUser() {
        return permissoes.contains(USER);
    }

    public static UsuarioAutenticado of(Usuario usuario) {
        var usuarioAutenticado = new UsuarioAutenticado();
        BeanUtils.copyProperties(usuario, usuarioAutenticado);
        usuarioAutenticado.setPermissoes(usuario
            .getPermissoes()
            .stream()
            .map(Permissao::getPermissao)
            .collect(Collectors.toList()));
        return usuarioAutenticado;
    }
}
