package br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;

import java.time.LocalTime;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.umaDieta;

public class PeriodoMocks {

    public static Periodo umPeriodoUsuario() {
        return Periodo
            .builder()
            .id(5)
            .descricao("Pré-Treino")
            .dieta(umaDieta())
            .hora(LocalTime.parse("20:00"))
            .build();
    }

    public static PeriodoRequest umPeriodoRequest() {
        return PeriodoRequest
            .builder()
            .id(5)
            .dietaId(1)
            .descricao("Treino")
            .hora(LocalTime.parse("20:00"))
            .build();
    }

    public static PeriodoResponse umPeriodoResponse() {
        return PeriodoResponse
            .builder()
            .id(1)
            .descricao("Manhã")
            .hora(LocalTime.parse("20:00"))
            .build();
    }
}
