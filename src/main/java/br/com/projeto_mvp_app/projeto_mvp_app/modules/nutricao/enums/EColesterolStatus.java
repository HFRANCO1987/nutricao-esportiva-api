package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums;

import lombok.Getter;

public enum EColesterolStatus {

    COLESTEROL_TOTAL_ALTO("Colesterol Total Alto"),
    COLESTEROL_TOTAL_BOM("Colesterol Total Bom"),
    HDL_BOM("HDL Bom"),
    HDL_BAIXO("HDL Baixo"),
    LDL_BOM("LDL Bom"),
    LDL_ALTO("LDL Alto");

    @Getter
    private String colesterolStatus;

    EColesterolStatus(String colesterolStatus) {
        this.colesterolStatus = colesterolStatus;
    }
}
