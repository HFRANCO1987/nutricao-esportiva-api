package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.predicate.AlimentoPredicate;
import lombok.Data;

@Data
public class AlimentoFiltros {

    private String descricao;
    private String categoria;

    public AlimentoPredicate toPredicate() {
        return new AlimentoPredicate()
            .comDescricao(descricao)
            .comCategoria(categoria);
    }
}
