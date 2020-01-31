package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EPeriodo {

    MANHA("Manhã"),
    ALMOCO("Almoço"),
    TARDE("Tarde"),
    NOITE("NOite");

    @Getter
    private String periodo;
}
