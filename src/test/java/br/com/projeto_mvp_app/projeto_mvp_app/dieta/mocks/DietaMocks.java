package br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.DietaAlimentoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.DietaCompletaResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.DietaRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.dieta.PeriodosAlimentosResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.AlimentoMocks.umAlimento;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.AlimentoMocks.umAlimentoResponse;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMocks.umPeriodoUsuario;

public class DietaMocks {

    public static DietaRequest umaDietaRequest() {
        return DietaRequest
            .builder()
            .id(1)
            .descricao("Teste")
            .build();
    }

    public static Dieta umaDieta() {
        return Dieta
            .builder()
            .id(1)
            .descricao("Teste")
            .dataCadastro(LocalDateTime.parse("2020-02-25T00:00"))
            .build();
    }

    public static DietaAlimentoRequest umaDietaAlimentoRequest() {
        return DietaAlimentoRequest
            .builder()
            .alimentoId(125)
            .dietaId(1)
            .periodoId(1)
            .build();
    }

    public static DietaCompletaResponse umaDietaCompletaResponse() {
        return DietaCompletaResponse
            .builder()
            .id(1)
            .descricao("Teste")
            .periodosAlimentos(List.of(umPeriodosAlimentosResponse()))
            .build();
    }

    public static PeriodosAlimentosResponse umPeriodosAlimentosResponse() {
        return PeriodosAlimentosResponse
            .builder()
            .id(1)
            .alimentos(List.of(umAlimentoResponse()))
            .descricao("Arroz")
            .build();
    }

    public static PeriodoAlimentoDieta umPeriodoAlimentoDieta() {
        return PeriodoAlimentoDieta
            .builder()
            .id(1)
            .alimento(umAlimento())
            .dieta(umaDieta())
            .periodo(umPeriodoUsuario())
            .quantidade(150.35)
            .build();
    }
}
