package br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.AnalisePesoAlturaResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuario;

public class PesoAlturaMocks {

    public static PesoAltura umPesoAltura() {
        return PesoAltura
            .builder()
            .altura(1.83)
            .dataCadastro(LocalDateTime.parse("2020-01-01T00:00"))
            .peso(94.5)
            .id(1)
            .usuario(umUsuario())
            .pesoAlturaAtual(EBoolean.V)
            .build();
    }

    public static PesoAltura umPesoAlturaAtual() {
        return PesoAltura
            .builder()
            .altura(1.83)
            .dataCadastro(LocalDateTime.now().minusDays(1))
            .peso(94.5)
            .id(1)
            .usuario(umUsuario())
            .pesoAlturaAtual(EBoolean.V)
            .build();
    }

    public static PesoAltura umPesoAlturaAnalise() {
        return PesoAltura
            .builder()
            .altura(1.83)
            .dataCadastro(LocalDateTime.parse("2020-02-25T00:00"))
            .peso(94.5)
            .id(1)
            .usuario(umUsuario())
            .pesoAlturaAtual(EBoolean.V)
            .build();
    }

    public static PesoAltura umPesoAlturaAnaliseAnterior() {
        return PesoAltura
            .builder()
            .altura(1.83)
            .dataCadastro(LocalDateTime.parse("2020-02-17T00:00"))
            .peso(94.5)
            .id(1)
            .usuario(umUsuario())
            .pesoAlturaAtual(EBoolean.V)
            .build();
    }

    public static AnalisePesoAlturaResponse umaAnalisePesoAlturaResponse() {
        return AnalisePesoAlturaResponse
            .builder()
            .data(LocalDate.now())
            .diaSemana("Terça-feira")
            .relacaoPeso(BigDecimal.valueOf(3.4))
            .peso(89.4)
            .build();
    }
}
