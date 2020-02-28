package br.com.projeto_mvp_app.projeto_mvp_app.esporte.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.UsuarioEsporte;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.repository.EsporteRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.repository.UsuarioEsporteRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.service.EsporteService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EsporteServiceTest {

    @InjectMocks
    private EsporteService esporteService;
    @Mock
    private EsporteRepository esporteRepository;
    @Mock
    private UsuarioEsporteRepository usuarioEsporteRepository;
    @Mock
    private AutenticacaoService autenticacaoService;

    @Test
    public void adicionarEsporteUsuario_deveAdicionarEsporteAoUsuario_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(usuarioEsporteRepository.existsByUsuarioIdAndEsporteId(anyInt(), anyInt()))
            .thenReturn(false);

        var response = esporteService.adicionarEsporteUsuario(1);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("O esporte foi vinculado ao usuário com sucesso!");

        verify(usuarioEsporteRepository, times(1)).save(any(UsuarioEsporte.class));
    }

    @Test
    public void adicionarEsporteUsuario_deveLancarException_quandoUsuarioJaPossuirEsporte() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(usuarioEsporteRepository.existsByUsuarioIdAndEsporteId(anyInt(), anyInt()))
            .thenReturn(true);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> esporteService.adicionarEsporteUsuario(1))
            .withMessage("Esse esporte já está vinculado a este usuário.");

        verify(usuarioEsporteRepository, times(0)).save(any(UsuarioEsporte.class));
    }

    @Test
    public void removerEsporteUsuario_deveRemoverEsporteAoUsuario_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var response = esporteService.removerEsporteUsuario(1);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("O esporte foi removido do usuário com sucesso!");

        verify(usuarioEsporteRepository, times(1))
            .deleteByUsuarioIdAndEsporteId(anyInt(), anyInt());
    }
}
