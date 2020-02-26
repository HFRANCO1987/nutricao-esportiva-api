package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.predicate.DietaPredicate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DietaFiltros {

    private String descricaoDieta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer usuarioId;
    private String alimento;
    private String descricaoPeriodo;
    private String categoria;

    public DietaPredicate toPredicate() {
        return new DietaPredicate()
            .comDescricao(descricaoDieta)
            .comDataCadastro(dataInicio, dataFim)
            .comUsuario(usuarioId)
            .comAlimento(alimento)
            .comPeriodo(descricaoPeriodo)
            .comCategoria(categoria);
    }
}
