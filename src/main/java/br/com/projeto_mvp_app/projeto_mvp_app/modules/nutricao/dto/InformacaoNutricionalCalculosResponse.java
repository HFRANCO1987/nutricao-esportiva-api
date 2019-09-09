package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.model.InformacaoNutricional;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoNutricionalCalculosResponse {

    private Integer id;
    private Float altura;
    private Float peso;
    private Float hdl;
    private Float colesterolTotal;
    private Float triglicerides;
    private Float glicose;
    private Float insulina;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime dataCadastroInformacao;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime dataUltimoExame;
    private Float ldl;
    private Float imc;
    private Float percentualDiabetes;
    private String imcStatus;

    @JsonIgnore
    private Float calcularImc() {
        return peso / (altura * altura);
    }

    @JsonIgnore
    private String definirStatusImc(Float imc) {
        return imc < 18.5
            ? ABAIXO.getImcStatus()
            : imc >= 18.5 && imc <= 24.9 ? IDEAL.getImcStatus()
            : imc >= 25 && imc <= 29.9 ? SOBREPESO.getImcStatus()
            : imc >= 30 && imc <= 34.9 ? OBESIDADE.getImcStatus()
            : imc >= 35 && imc <= 39.9 ? OBESIDADE_GRAVE.getImcStatus()
            : OBESIDADE_MORBIDA.getImcStatus();
    }

    @JsonIgnore
    private Float calcularLdl() {
        return colesterolTotal - hdl - (triglicerides / 5);
    }

    public static InformacaoNutricionalCalculosResponse of(InformacaoNutricional informacaoNutricional) {
        var informacoesNutricionaisCalculadas = new InformacaoNutricionalCalculosResponse();
        BeanUtils.copyProperties(informacaoNutricional, informacoesNutricionaisCalculadas);
        informacoesNutricionaisCalculadas.setImc(informacoesNutricionaisCalculadas.calcularImc());
        informacoesNutricionaisCalculadas.setImcStatus(informacoesNutricionaisCalculadas
            .definirStatusImc(informacoesNutricionaisCalculadas.getImc()));
        informacoesNutricionaisCalculadas.setLdl(informacoesNutricionaisCalculadas.calcularLdl());
        return informacoesNutricionaisCalculadas;
    }
}
