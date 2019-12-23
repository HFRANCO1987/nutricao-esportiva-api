package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("MethodLength")
public class CaloriaResponse {

    private static final Integer NUMERO_CASAS_DECIMAIS = 2;
    private static final Double EMAGRECER_MINIMO = 20.0;
    private static final Double EMAGRECER_MAXIMO = 24.9;
    private static final Double MANTER_MINIMO = 25.0;
    private static final Double MANTER_MAXIMO = 29.9;
    private static final Double ENGORDAR_MINIMO = 30.0;
    private static final Double ENGORDAR_MAXIMO = 35.0;
    private static final Double MEDIA = 2.0;

    private BigDecimal emagrecerMinimo;
    private BigDecimal emagrecerMaximo;
    private BigDecimal emagrecerMedia;
    private BigDecimal manterMinimo;
    private BigDecimal manterMaximo;
    private BigDecimal manterMedia;
    private BigDecimal engordarMinimo;
    private BigDecimal engordarMaximo;
    private BigDecimal engordarMedia;

    public static CaloriaResponse of(Double peso) {
        var response = new CaloriaResponse();
        // Emagrecer
        response.setEmagrecerMinimo(BigDecimal.valueOf(peso * EMAGRECER_MINIMO)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        response.setEmagrecerMaximo(BigDecimal.valueOf(peso * EMAGRECER_MAXIMO)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        response.setEmagrecerMedia(BigDecimal.valueOf((response.getEmagrecerMinimo().doubleValue()
            + response.getEmagrecerMaximo().doubleValue()) / MEDIA)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        // Manter
        response.setManterMinimo(BigDecimal.valueOf(peso * MANTER_MINIMO)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        response.setManterMaximo(BigDecimal.valueOf(peso * MANTER_MAXIMO)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        response.setManterMedia(BigDecimal.valueOf((response.getManterMinimo().doubleValue()
            + response.getManterMaximo().doubleValue()) / MEDIA)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        // Engordar
        response.setEngordarMinimo(BigDecimal.valueOf(peso * ENGORDAR_MINIMO)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        response.setEngordarMaximo(BigDecimal.valueOf(peso * ENGORDAR_MAXIMO)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        response.setEngordarMedia(BigDecimal.valueOf((response.getEngordarMinimo().doubleValue()
            + response.getEngordarMaximo().doubleValue()) / MEDIA)
            .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP));
        return response;
    }
}
