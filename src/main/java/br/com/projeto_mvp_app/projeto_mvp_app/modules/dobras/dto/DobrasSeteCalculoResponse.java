package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
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
@SuppressWarnings("MagicNumber")
public class DobrasSeteCalculoResponse {

    private static final Integer QUADRADO = 2;
    private BigDecimal densidadeCorporal;
    private BigDecimal percentualGordura;

    public static DobrasSeteCalculoResponse of(Dobras dobras) {
        var response = new DobrasSeteCalculoResponse();
        response.validarCamposPreenchidos(dobras);
        response.setDensidadeCorporal(calcularDensidadeCorporalSeteDobras(dobras));
        response.setPercentualGordura(calcularPercentualDeGordura(response.getDensidadeCorporal()));
        return response;
    }

    private void validarCamposPreenchidos(Dobras dobras) {
        if (isEmpty(dobras.getPeito())
            || isEmpty(dobras.getAxila())
            || isEmpty(dobras.getTricep())
            || isEmpty(dobras.getSubescapular())
            || isEmpty(dobras.getAbdominal())
            || isEmpty(dobras.getSuprailiaca())
            || isEmpty(dobras.getCoxa())) {
            throw new ValidacaoException("É necessário que todas as dobras sejam "
                + "preenchidas para realizar o cálculo das 7 dobras.");
        }
    }

    private static BigDecimal calcularDensidadeCorporalSeteDobras(Dobras dobras) {
        var idade = dobras.getUsuario().getIdade();
        var somaDobras = somarDobras(dobras);
        if (dobras.getUsuario().isMasculino()) {
            return  getDuasCasas(
                1.112
                    - (0.00043499 * somaDobras)
                    + (0.00000055 * Math.pow(somaDobras, QUADRADO) )
                    - (0.00028826 * idade));
        } else {
            return  getDuasCasas(
                1.097
                    - (0.00046971 * somaDobras)
                    + (0.00000056 * Math.pow(somaDobras, QUADRADO) )
                    - (0.00012828 * idade));
        }
    }

    private static Double somarDobras(Dobras dobras) {
        return dobras.getPeito()
            + dobras.getAxila()
            + dobras.getTricep()
            + dobras.getSubescapular()
            + dobras.getAbdominal()
            + dobras.getSuprailiaca()
            + dobras.getCoxa();
    }

    private static BigDecimal calcularPercentualDeGordura(BigDecimal densidadeCorporal) {
        return getDuasCasas(495 / densidadeCorporal.doubleValue() - 450);
    }
}
