package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class AnalisePesoAlturaResponse {

    private static final Integer INDICE_PRIMEIRO_CARACTERE = 0;
    private static final Integer INDICE_SEGUNDO_CARACTERE = 1;
    private static final Integer NUMERO_CASAS_DECIMAIS = 2;

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
            response.setRelacaoPeso(BigDecimal.valueOf(atual.getPeso() - proximo.getPeso())
                .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
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
