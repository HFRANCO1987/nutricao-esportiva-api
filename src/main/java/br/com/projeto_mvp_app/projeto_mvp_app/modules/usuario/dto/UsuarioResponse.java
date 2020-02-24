package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Permissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private String nome;
    private String email;
    private String cpf;
    private List<Permissao> permissoes;

    public static UsuarioResponse of(Usuario usuario) {
        return UsuarioResponse
            .builder()
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .cpf(usuario.getCpf())
            .permissoes(usuario.getPermissoes())
            .build();
    }
}
