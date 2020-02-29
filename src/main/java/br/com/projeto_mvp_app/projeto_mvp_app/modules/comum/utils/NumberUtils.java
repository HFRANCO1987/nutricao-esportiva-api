package br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    private static final Integer UMA_CASA_DECIMAL = 1;
    private static final Integer DUAS_CASAS_DECIMAIS = 2;

    public static BigDecimal getUmaCasa(Double numero) {
        return BigDecimal
            .valueOf(numero)
            .setScale(UMA_CASA_DECIMAL, RoundingMode.HALF_UP);
    }

    public static BigDecimal getUmaCasa(BigDecimal numero) {
        return numero.setScale(UMA_CASA_DECIMAL, RoundingMode.HALF_UP);
    }

    public static BigDecimal getDuasCasas(Double numero) {
        return BigDecimal
            .valueOf(numero)
            .setScale(DUAS_CASAS_DECIMAIS, RoundingMode.HALF_UP);
    }

    public static BigDecimal getDuasCasas(BigDecimal numero) {
        return numero.setScale(DUAS_CASAS_DECIMAIS, RoundingMode.HALF_UP);
    }
}
