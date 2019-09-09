package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.enums;

import lombok.Getter;

public enum EColesterolStatus {
    ABAIXO("Abaixo do Peso"),
    IDEAL("Peso Ideal"),
    SOBREPESO("Sobrepeso"),
    OBESIDADE("Obesidade (Grau I)"),
    OBESIDADE_GRAVE("Obesidade Grave (Grau II)"),
    OBESIDADE_MORBIDA("Obesidade Mórbida (Grau III)");

    @Getter
    private String colesterolStatus;

    EColesterolStatus(String colesterolStatus) {
        this.colesterolStatus = colesterolStatus;
    }
}
