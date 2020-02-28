package br.com.projeto_mvp_app.projeto_mvp_app.esporte.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.Esporte;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.UsuarioEsporte;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuario;

public class EsporteMocks {

    public static Esporte umEsporte() {
        return Esporte
            .builder()
            .id(1)
            .atividade("Corrida")
            .kcalHoraMax(900.00)
            .kcalHoraMin(500.0)
            .kcalMinMax(8.33)
            .kcalMinMax(15.00)
            .build();
    }

    public static UsuarioEsporte umUsuarioEsporte() {
        return UsuarioEsporte
            .builder()
            .id(1)
            .usuario(umUsuario())
            .esporte(umEsporte())
            .build();
    }
}
