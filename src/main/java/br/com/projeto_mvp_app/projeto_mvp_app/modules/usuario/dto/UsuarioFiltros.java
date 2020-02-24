package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.predicate.UsuarioPredicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFiltros {

    private String nome;
    private String email;
    private String cpf;
    private EPermissao permissao;

    public UsuarioPredicate toPredicate() {
        return new UsuarioPredicate()
            .comNome(nome)
            .comEmail(email)
            .comCpf(cpf)
            .comPermissao(permissao);
    }
}
