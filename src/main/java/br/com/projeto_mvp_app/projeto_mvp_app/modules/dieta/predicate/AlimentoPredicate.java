package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.predicate;

import br.com.projeto_mvp_app.projeto_mvp_app.config.PredicateBase;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.QAlimento.alimento;
import static org.springframework.util.ObjectUtils.isEmpty;

public class AlimentoPredicate extends PredicateBase {

    public AlimentoPredicate comDescricao(String descricao) {
        if (!isEmpty(descricao)) {
            builder.and(alimento.descricao.containsIgnoreCase(descricao));
        }
        return this;
    }

    public AlimentoPredicate comCategoria(String categoria) {
        if (!isEmpty(categoria)) {
            builder.and(alimento.categoria.descricao.containsIgnoreCase(categoria));
        }
        return this;
    }
}
