package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.predicate;

import br.com.projeto_mvp_app.projeto_mvp_app.config.PredicateBase;

import static org.springframework.util.ObjectUtils.isEmpty;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.QUsuario.usuario;

public class UsuarioPredicate extends PredicateBase {

    public UsuarioPredicate comNome(String nome) {
        if (!isEmpty(nome)) {
            builder.and(usuario.nome.containsIgnoreCase(nome));
        }
        return this;
    }

    public UsuarioPredicate comEmail(String email) {
        if (!isEmpty(email)) {
            builder.and(usuario.email.containsIgnoreCase(email));
        }
        return this;
    }

    public UsuarioPredicate comCpf(String cpf) {
        if (!isEmpty(cpf)) {
            builder.and(usuario.cpf.containsIgnoreCase(cpf));
        }
        return this;
    }

    public UsuarioPredicate comPermissao(String permissao) {
        if (!isEmpty(permissao)) {
            builder.and(usuario.permissao.descricao.containsIgnoreCase(permissao));
        }
        return this;
    }
}