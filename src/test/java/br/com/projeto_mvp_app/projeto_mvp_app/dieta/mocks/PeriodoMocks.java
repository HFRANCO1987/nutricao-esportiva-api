package br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuario;

public class PeriodoMocks {

    public static Periodo umPeriodoPadrao() {
        return Periodo
            .builder()
            .id(1)
            .descricao("Manhã")
            .usuario(null)
            .padrao(EBoolean.V)
            .build();
    }

    public static Periodo umPeriodoUsuario() {
        return Periodo
            .builder()
            .id(5)
            .descricao("Pré-Treino")
            .usuario(umUsuario())
            .padrao(EBoolean.F)
            .build();
    }

    public static PeriodoRequest umPeriodoRequest() {
        return PeriodoRequest
            .builder()
            .id(5)
            .descricao("Treino")
            .build();
    }

    public static PeriodoResponse umPeriodoResponse() {
        return PeriodoResponse
            .builder()
            .id(1)
            .descricao("Manhã")
            .build();
    }
}
