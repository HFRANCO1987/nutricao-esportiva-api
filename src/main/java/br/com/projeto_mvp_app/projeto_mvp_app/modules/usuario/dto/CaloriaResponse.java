package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils.NumberUtils.getDuasCasas;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("MethodLength")
public class CaloriaResponse {

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
        response.setEmagrecerMinimo(getDuasCasas(peso * EMAGRECER_MINIMO));
        response.setEmagrecerMaximo(getDuasCasas(peso * EMAGRECER_MAXIMO));
        response.setEmagrecerMedia(getDuasCasas(response.getEmagrecerMinimo().doubleValue()
            + response.getEmagrecerMaximo().doubleValue() / MEDIA));
        // Manter
        response.setManterMinimo(getDuasCasas(peso * MANTER_MINIMO));
        response.setManterMaximo(getDuasCasas(peso * MANTER_MAXIMO));
        response.setManterMedia(getDuasCasas(response.getManterMinimo().doubleValue()
            + response.getManterMaximo().doubleValue() / MEDIA));
        // Engordar
        response.setEngordarMinimo(getDuasCasas(peso * ENGORDAR_MINIMO));
        response.setEngordarMaximo(getDuasCasas(peso * ENGORDAR_MAXIMO));
        response.setEngordarMedia(getDuasCasas((response.getEngordarMinimo().doubleValue()
            + response.getEngordarMaximo().doubleValue()) / MEDIA));
        return response;
    }
}
