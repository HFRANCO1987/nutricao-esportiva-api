package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums.EPeriodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.predicate.DietaPredicate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DietaFiltros {

    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer usuarioId;
    private String alimento;
    private EPeriodo codigoPeriodo;
    private String categoria;

    public DietaPredicate toPredicate() {
        return new DietaPredicate()
            .comDescricao(descricao)
            .comDataCadastro(dataInicio, dataFim)
            .comUsuario(usuarioId)
            .comAlimento(alimento)
            .comPeriodo(codigoPeriodo)
            .comCategoria(categoria);
    }
}
