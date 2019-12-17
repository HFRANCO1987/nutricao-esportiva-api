package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

import static java.math.BigInteger.ZERO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"MagicNumber", "MethodLength"})
public class UsuarioAnalisePesoResponse {

    private static final String DIA = " dia";
    private static final String DIAS = " dias";
    private static final String MES = " mês";
    private static final String MESES = " meses";
    private static final String ANO = " ano";
    private static final String ANOS = " anos";
    private static final String PERDEU = "perdeu";
    private static final String AUMENTOU = " aumentou";
    private static final String MANTEVE = " manteve";
    private static final Integer NUMERO_CASAS_DECIMAIS = 2;
    private static DecimalFormat df = new DecimalFormat("0.00");

    private String mensagem;
    private Double pesoAtual;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPesoAtual;
    private Double pesoAnterior;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPesoAnterior;
    private BigDecimal diferencaPeso;
    private BigDecimal percentualPeso;
    private String diferencaPeriodo;

    public static UsuarioAnalisePesoResponse of(String nome, PesoAltura atual, PesoAltura anterior) {
        var response = new UsuarioAnalisePesoResponse();
        response.setPesoAtual(atual.getPeso());
        response.setDataPesoAtual(atual.getDataCadastro().toLocalDate());
        response.setPesoAnterior(anterior.getPeso());
        response.setDataPesoAnterior(anterior.getDataCadastro().toLocalDate());
        response.setDiferencaPeriodo(getDiferencaPeriodo(atual.getDataCadastro().toLocalDate(),
            anterior.getDataCadastro().toLocalDate()));
        response.setDiferencaPeso(calcularDiferencaPeso(atual.getPeso(), anterior.getPeso()));
        response.setPercentualPeso(calcularPercentualPeso(atual.getPeso(), anterior.getPeso()));
        response.setMensagem(definirMensagem(nome, atual.getPeso(), anterior.getPeso(),
            response.getDiferencaPeriodo()));
        return response;
    }

    private static String getDiferencaPeriodo(LocalDate atual, LocalDate anterior) {
        var ano = Period.between(anterior, atual).getYears();
        var mes = Period.between(anterior, atual).getMonths();
        var dia = Period.between(anterior, atual).getDays();
        if (ano > 1) {
            return ano + ANOS;
        }
        if (ano == 1) {
            return 1 + ANO;
        }
        if (ano == 0) {
            if (mes <= 12 && mes > 1) {
                return mes + MESES;
            }
            if (mes == 1) {
                return 1 + MES;
            }
            if (mes == 0 && dia > 1) {
                return dia + DIAS;
            }
        }
        return 1 + DIA;
    }

    private static String getDiferencaPeso(Double pesoAtual, Double pesoAnterior) {
        return pesoAtual > pesoAnterior ? AUMENTOU : pesoAtual.equals(pesoAnterior) ? MANTEVE : PERDEU;
    }

    private static BigDecimal calcularDiferencaPeso(Double pesoAtual, Double pesoAnterior) {
        return new BigDecimal(Math.abs(pesoAtual - pesoAnterior)).setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP);
    }

    private static BigDecimal calcularPercentualPeso(Double pesoAtual, Double pesoAnterior) {
        if (pesoAtual < pesoAnterior) {
            return new BigDecimal(Math.abs(100 - (pesoAtual / pesoAnterior) * 100))
                .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP);
        }
        if (pesoAtual > pesoAnterior) {
            return new BigDecimal(Math.abs(100 - (pesoAnterior / pesoAtual) * 100))
                .setScale(NUMERO_CASAS_DECIMAIS, RoundingMode.HALF_UP);
        } else {
            return new BigDecimal(ZERO);
        }
    }

    private static String definirMensagem(String nome,
                                          Double pesoAtual,
                                          Double pesoAnterior,
                                          String diferencaPeriodo) {
        var diferencaPeso = getDiferencaPeso(pesoAtual, pesoAnterior);
        if (diferencaPeso.equals(PERDEU)) {
            return "Olá, " + nome + ", você perdeu " + calcularDiferencaPeso(pesoAtual, pesoAnterior)
                + "kg (" + calcularPercentualPeso(pesoAtual, pesoAnterior) + "%) em " + diferencaPeriodo;
        }
        if (diferencaPeso.equals(AUMENTOU)) {
            return "Olá, " + nome + ", você aumentou " + calcularDiferencaPeso(pesoAtual, pesoAnterior)
                + "kg (" + calcularPercentualPeso(pesoAtual, pesoAnterior) + "%) em " + diferencaPeriodo;
        }
        return "Olá, " + nome + ", você manteve seu peso atual de " + pesoAtual + "kg";
    }
}