package br.com.projeto_mvp_app.projeto_mvp_app.dobras.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasSeteCalculoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasTresCalculoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuario;

public class DobrasMocks {

    public static Dobras umaDobraAtual() {
        return Dobras
            .builder()
            .id(1)
            .abdominal(132.4)
            .coxa(123.1)
            .peito(146.4)
            .subescapular(132.4)
            .suprailiaca(199.1)
            .tricep(200.2)
            .axila(156.4)
            .usuario(umUsuario())
            .atual(EBoolean.V)
            .build();
    }

    public static Dobras umaDobraAntiga() {
        return Dobras
            .builder()
            .id(1)
            .abdominal(132.4)
            .coxa(123.1)
            .peito(146.4)
            .subescapular(132.4)
            .suprailiaca(199.1)
            .tricep(200.2)
            .axila(156.4)
            .usuario(umUsuario())
            .atual(EBoolean.F)
            .build();
    }

    public static DobrasRequest umaDobraRequest() {
        return DobrasRequest
            .builder()
            .abdominal(132.4)
            .coxa(123.1)
            .peito(146.4)
            .subescapular(132.4)
            .suprailiaca(199.1)
            .tricep(200.2)
            .axila(156.4)
            .build();
    }

    public static DobrasTresCalculoResponse umaDobraTresCalculoResponse() {
        return DobrasTresCalculoResponse.of(umaDobraAtual());
    }

    public static DobrasSeteCalculoResponse umaDobraSeteCalculoResponse() {
        return DobrasSeteCalculoResponse.of(umaDobraAtual());
    }

    public static DobrasResponse umaDobraResponse() {
        return DobrasResponse
            .builder()
            .tresDobras(umaDobraTresCalculoResponse())
            .seteDobras(umaDobraSeteCalculoResponse())
            .build();
    }
}
