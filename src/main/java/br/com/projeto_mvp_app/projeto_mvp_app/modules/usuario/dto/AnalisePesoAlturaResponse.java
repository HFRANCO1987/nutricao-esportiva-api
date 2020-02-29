package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getDuasCasas;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalisePesoAlturaResponse {

    private static final Integer INDICE_PRIMEIRO_CARACTERE = 0;
    private static final Integer INDICE_SEGUNDO_CARACTERE = 1;

    private String diaSemana;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private Double peso;
    private BigDecimal relacaoPeso;

    public static AnalisePesoAlturaResponse of(PesoAltura atual, PesoAltura proximo, boolean ultimo) {
        var response = new AnalisePesoAlturaResponse();
        if (ultimo) {
            response.setRelacaoPeso(new BigDecimal(BigInteger.ZERO));
        } else {
            response.setRelacaoPeso(getDuasCasas(atual.getPeso() - proximo.getPeso()));
        }
        response.setPeso(atual.getPeso());
        response.setData(atual.getDataCadastro().toLocalDate());
        response.setDiaSemana(response.recuperarDiaDaSemana(atual.getDataCadastro().toLocalDate()));
        return response;
    }

    private String recuperarDiaDaSemana(LocalDate dataAtual) {
        var diaSemana = dataAtual.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt"));
        return diaSemana.substring(INDICE_PRIMEIRO_CARACTERE, INDICE_SEGUNDO_CARACTERE).toUpperCase()
            + diaSemana.substring(INDICE_SEGUNDO_CARACTERE);
    }
}
