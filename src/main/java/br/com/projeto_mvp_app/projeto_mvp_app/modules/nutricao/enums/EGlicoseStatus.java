package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums;

import lombok.Getter;

public enum EGlicoseStatus {

    GLICOSE_BAIXA("Glicose Baixa - Chances de Hipoglicemia"),
    GLICOSE_NORMAL("Glicose Normal"),
    GLICOSE_ALTERADA("Glicose Alterada"),
    GLICOSE_ALTA("Glicose Alta - Pré-Diabetes"),
    GLICOSE_DIABETES("Glicose Muito Alta - Risco de Diabetes");

    @Getter
    private String glicoseStatus;

    EGlicoseStatus(String glicoseStatus) {
        this.glicoseStatus = glicoseStatus;
    }
}
