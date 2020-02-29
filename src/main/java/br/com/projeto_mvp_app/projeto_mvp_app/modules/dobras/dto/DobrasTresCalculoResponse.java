package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
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
@SuppressWarnings("MagicNumber")
public class DobrasTresCalculoResponse {

    private static final Integer QUADRADO = 2;
    private BigDecimal densidadeCorporal;
    private BigDecimal percentualGordura;

    public static DobrasTresCalculoResponse of(Dobras dobras) {
        var response = new DobrasTresCalculoResponse();
        response.setDensidadeCorporal(calcularDensidadeCorporalTresDobras(dobras));
        response.setPercentualGordura(calcularPercentualDeGordura(response.getDensidadeCorporal()));
        return response;
    }

    private static BigDecimal calcularDensidadeCorporalTresDobras(Dobras dobras) {
        var idade = dobras.getUsuario().getIdade();
        var somaDobras = somarDobras(dobras);
        if (dobras.getUsuario().isMasculino()) {
            return  getDuasCasas(
                1.10938
                    - (0.0008267 * somaDobras)
                    + (0.0000016 * Math.pow(somaDobras, QUADRADO) )
                    - (0.0002574 * idade));
        } else {
            return  getDuasCasas(
                1.0994921
                    - (0.0009929 * somaDobras)
                    + (0.0000023 * Math.pow(somaDobras, QUADRADO) )
                    - (0.0001392 * idade));
        }
    }

    private static Double somarDobras(Dobras dobras) {
        return dobras.getPeito()
            + dobras.getAbdominal()
            + dobras.getCoxa();
    }

    private static BigDecimal calcularPercentualDeGordura(BigDecimal densidadeCorporal) {
        return getDuasCasas(495 / densidadeCorporal.doubleValue() - 450);
    }
}
