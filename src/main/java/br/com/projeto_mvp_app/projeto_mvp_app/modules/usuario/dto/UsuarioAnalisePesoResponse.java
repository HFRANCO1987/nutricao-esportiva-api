package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"MagicNumber", "MethodLength"})
public class UsuarioAnalisePesoResponse {

    private static final String DIA = " dia";
    private static final String DIAS = " dias";
    private static final String SEMANA = " semana";
    private static final String SEMANAS = " semanas";
    private static final String MES = " mês";
    private static final String MESES = " meses";
    private static final String ANO = " ano";
    private static final String ANOS = " ano";
    private static final String PERDEU = "perdeu";
    private static final String AUMENTOU = " aumentou";
    private static final String MANTEVE = " manteve";

    private String mensagem;
    private Double diferencaPeso;
    private Double percentualPeso;
    private String diferencaPeriodo;

    public static UsuarioAnalisePesoResponse of(String nome, PesoAltura atual, PesoAltura anterior) {
        var response = new UsuarioAnalisePesoResponse();
        response.setDiferencaPeriodo(getDiferencaPeriodo(atual.getDataCadastro().toLocalDate(),
            anterior.getDataCadastro().toLocalDate()));
        response.setDiferencaPeso(calcularDiferencaPeso(atual.getPeso(), anterior.getPeso()));
        response.setPercentualPeso(calcularPercentualPeso(atual.getPeso(), anterior.getPeso()));
        response.setMensagem(definirMensagem(nome, atual.getPeso(), anterior.getPeso(),
            response.getDiferencaPeriodo()));
        return response;
    }

    private static String getDiferencaPeriodo(LocalDate atual, LocalDate anterior) {
        if (Period.between(anterior, atual).getDays() == 1) {
            return 1 + DIA;
        }
        if (Period.between(anterior, atual).getDays() <= 6) {
            return Period.between(anterior, atual).getDays() + DIAS;
        }
        if (Period.between(anterior, atual).getDays() == 7) {
            return 1 + SEMANA;
        }
        if (Period.between(anterior, atual).getMonths() < 1) {
            return Period.between(anterior, atual).getDays() / 7 + SEMANAS;
        }
        if (Period.between(anterior, atual).getMonths() == 1) {
            return 1 + MES;
        }
        if (Period.between(anterior, atual).getMonths() < 12) {
            return Period.between(anterior, atual).getMonths() + MESES;
        }
        if (Period.between(anterior, atual).getYears() == 1) {
            return 1 + ANO;
        } else {
            return Period.between(anterior, atual).getYears() + ANOS;
        }
    }

    private static String getDiferencaPeso(Double pesoAtual, Double pesoAnterior) {
        return pesoAtual > pesoAnterior ? AUMENTOU : pesoAtual.equals(pesoAnterior) ? MANTEVE : PERDEU;
    }

    private static Double calcularDiferencaPeso(Double pesoAtual, Double pesoAnterior) {
        return Math.abs(pesoAtual - pesoAnterior);
    }

    private static Double calcularPercentualPeso(Double pesoAtual, Double pesoAnterior) {
        if (pesoAtual < pesoAnterior) {
            return Math.abs(100 - (pesoAtual / pesoAnterior) * 100);
        }
        if (pesoAtual > pesoAnterior) {
            return Math.abs(100 - (pesoAnterior / pesoAtual) * 100);
        } else {
            return 0.0;
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
            return "Olá, " + nome + ", você perdeu " + calcularDiferencaPeso(pesoAtual, pesoAnterior)
                + "kg (" + calcularPercentualPeso(pesoAtual, pesoAnterior) + "%) em " + diferencaPeriodo;
        }
        return "Olá, " + nome + ", você manteve seu peso atual de " + pesoAtual + "kg";
    }
}
