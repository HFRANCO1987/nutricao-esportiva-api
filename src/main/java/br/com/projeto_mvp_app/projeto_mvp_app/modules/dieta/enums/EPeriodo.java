package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
public enum EPeriodo {

    MANHA("Manhã", LocalTime.parse("08:00")),
    ALMOCO("Almoço", LocalTime.parse("12:00")),
    TARDE("Tarde", LocalTime.parse("15:30")),
    NOITE("Noite", LocalTime.parse("20:00"));

    @Getter
    private String periodo;
    @Getter
    private LocalTime hora;
}
