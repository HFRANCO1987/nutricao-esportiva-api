package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getDuasCasas;
import static org.springframework.util.ObjectUtils.isEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoResponse {

    private Integer id;
    private String descricao;
    private String categoria;
    private String imagem;
    private BigDecimal quantidade;
    private AlimentoInformacoes informacoes;

    public static AlimentoResponse of(Double quantidade, Alimento alimento) {
        return AlimentoResponse
            .builder()
            .id(alimento.getId())
            .descricao(alimento.getDescricao())
            .categoria(alimento.getCategoria().getDescricao())
            .imagem(alimento.getImagem())
            .informacoes(AlimentoInformacoes.of(alimento))
            .quantidade(!isEmpty(quantidade)
                ? getDuasCasas(quantidade)
                : BigDecimal.ZERO)
            .build();
    }
}
