package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoResponse {

    private Integer id;
    private String descricao;
    private String categoria;
    private AlimentoInformacoes informacoes;

    public static AlimentoResponse of(Alimento alimento) {
        return AlimentoResponse
            .builder()
            .id(alimento.getId())
            .descricao(alimento.getDescricao())
            .categoria(alimento.getCategoria().getDescricao())
            .informacoes(AlimentoInformacoes.of(alimento))
            .build();
    }
}
