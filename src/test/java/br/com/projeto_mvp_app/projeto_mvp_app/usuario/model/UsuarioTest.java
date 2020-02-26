package br.com.projeto_mvp_app.projeto_mvp_app.usuario.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.*;
import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuario;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class UsuarioTest {

    @Test
    public void of_deveRetornarModelUsuario_quandoVierDeRequest() {
        var usuario = Usuario.of(umUsuarioRequest());
        assertThat(usuario).isNotNull();
        assertThat(usuario.getNome()).isEqualTo("Victor Hugo Negrisoli");
        assertThat(usuario.getEmail()).isEqualTo("victorhugonegrisoli.ccs@gmail.com");
        assertThat(usuario.getPermissoes()).isEqualTo(List.of(umaPermissaoUser()));
        assertThat(usuario.getId()).isEqualTo(1);
    }

    @Test
    public void getIdade_deveRetornarIdadeCorreta_quandoUsuarioEstiverComDataNascimento() {
        var usuario = umUsuario();
        usuario.setDataNascimento(LocalDate.now().minusYears(25));
        assertThat(usuario.getIdade()).isEqualTo(25);
    }

    @Test
    public void isMasculino_deveRetornarSexoCorreto_quandoUsuarioForMasculino() {
        assertThat(umUsuario().isMasculino()).isEqualTo(true);
    }

    @Test
    public void isMasculino_deveRetornarSexoCorreto_quandoUsuarioNaoForFeminino() {
        assertThat(umUsuario().isFeminino()).isEqualTo(false);
    }
}
