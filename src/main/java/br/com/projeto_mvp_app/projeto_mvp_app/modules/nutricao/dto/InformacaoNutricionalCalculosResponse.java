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

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EColesterolStatus.HDL_BAIXO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EColesterolStatus.HDL_BOM;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EColesterolStatus.COLESTEROL_TOTAL_ALTO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EColesterolStatus.COLESTEROL_TOTAL_BOM;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EColesterolStatus.LDL_ALTO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EColesterolStatus.LDL_BOM;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EGlicoseStatus.GLICOSE_BAIXA;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EGlicoseStatus.GLICOSE_NORMAL;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EGlicoseStatus.GLICOSE_ALTERADA;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EGlicoseStatus.GLICOSE_ALTA;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EGlicoseStatus.GLICOSE_DIABETES;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.ABAIXO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.IDEAL;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.SOBREPESO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.OBESIDADE;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.OBESIDADE_GRAVE;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums.EImcStatus.OBESIDADE_MORBIDA;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("PMD.TooManyStaticImports")
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
    private String imcStatus;
    private String colesterolStatus;
    private String hdlStatus;
    private String ldlStatus;
    private String glicoseStatus;

    @JsonIgnore
    private static final Float MAXIMO_ABAIXO = 18.5f;
    @JsonIgnore
    private static final Float MAXIMO_IDEAL = 24.9f;
    @JsonIgnore
    private static final Float MINIMO_SOBREPESO = 25f;
    @JsonIgnore
    private static final Float MAXIMO_SOBREPESO = 29.9f;
    @JsonIgnore
    private static final Float MINIMO_OBESIDADE = 30f;
    @JsonIgnore
    private static final Float MAXIMO_OBESIDADE = 34.9f;
    @JsonIgnore
    private static final Float MINIMO_OBESIDADE_GRAVE = 34.9f;
    @JsonIgnore
    private static final Float MAXIMO_OBESIDADE_GRAVE = 39.9f;
    @JsonIgnore
    private static final Float MAXIMO_COLESTEROL_TOTAL = 190f;
    @JsonIgnore
    private static final Float MINIMO_HDL = 40f;
    @JsonIgnore
    private static final Float MINIMO_LDL = 100f;
    @JsonIgnore
    private static final Integer DIVISOR_CALCULO_LDL = 5;
    @JsonIgnore
    private static final Integer GLICOSE_BAIXA_INDICE = 70;
    @JsonIgnore
    private static final Integer GLICOSE_NORMAL_INDICE = 100;
    @JsonIgnore
    private static final Integer GLICOSE_ALTA_INDICE = 110;
    @JsonIgnore
    private static final Integer GLICOSE_DIABETES_INDICE = 120;

    @JsonIgnore
    private Float calcularImc() {
        return peso / (altura * altura);
    }

    @JsonIgnore
    private String definirStatusImc(Float imc) {
        return imc < MAXIMO_ABAIXO
            ? ABAIXO.getImcStatus()
            : imc >= MAXIMO_ABAIXO && imc <= MAXIMO_IDEAL ? IDEAL.getImcStatus()
            : imc >= MINIMO_SOBREPESO && imc <= MAXIMO_SOBREPESO ? SOBREPESO.getImcStatus()
            : imc >= MINIMO_OBESIDADE && imc <= MAXIMO_OBESIDADE ? OBESIDADE.getImcStatus()
            : imc >= MINIMO_OBESIDADE_GRAVE && imc <= MAXIMO_OBESIDADE_GRAVE ? OBESIDADE_GRAVE.getImcStatus()
            : OBESIDADE_MORBIDA.getImcStatus();
    }

    @JsonIgnore
    private Float calcularLdl() {
        return colesterolTotal - hdl - (triglicerides / DIVISOR_CALCULO_LDL);
    }

    @JsonIgnore
    private String definirColesterolTotal(Float colesterolTotal) {
        return colesterolTotal <= MAXIMO_COLESTEROL_TOTAL ? COLESTEROL_TOTAL_BOM.getColesterolStatus()
            : COLESTEROL_TOTAL_ALTO.getColesterolStatus();
    }

    @JsonIgnore
    private String definirHdl(Float hdl) {
        return hdl < MINIMO_HDL ? HDL_BAIXO.getColesterolStatus()
            : HDL_BOM.getColesterolStatus();
    }

    @JsonIgnore
    private String definirLdl(Float ldl) {
        return ldl < MINIMO_LDL ? LDL_BOM.getColesterolStatus()
            : LDL_ALTO.getColesterolStatus();
    }

    @JsonIgnore
    private String definirGlicoseStatus(Float glicose) {
        return glicose <= GLICOSE_BAIXA_INDICE ? GLICOSE_BAIXA.getGlicoseStatus()
            : glicose > GLICOSE_BAIXA_INDICE && glicose <= GLICOSE_NORMAL_INDICE ? GLICOSE_NORMAL.getGlicoseStatus()
            : glicose > GLICOSE_NORMAL_INDICE && glicose <= GLICOSE_ALTA_INDICE ? GLICOSE_ALTERADA.getGlicoseStatus()
            : glicose > GLICOSE_ALTA_INDICE && glicose <= GLICOSE_DIABETES_INDICE ? GLICOSE_ALTA.getGlicoseStatus()
            : GLICOSE_DIABETES.getGlicoseStatus();
    }

    public static InformacaoNutricionalCalculosResponse of(InformacaoNutricional informacaoNutricional) {
        var info = new InformacaoNutricionalCalculosResponse();
        BeanUtils.copyProperties(informacaoNutricional, info);
        info.setImc(info.calcularImc());
        info.setImcStatus(info.definirStatusImc(info.getImc()));
        info.setLdl(info.calcularLdl());
        info.setColesterolStatus(info.definirColesterolTotal(info.getColesterolTotal()));
        info.setHdlStatus(info.definirHdl(info.getHdl()));
        info.setLdlStatus(info.definirLdl(info.getLdl()));
        info.setGlicoseStatus(info.definirGlicoseStatus(info.getGlicose()));
        return info;
    }
}
