package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMocks.umPeriodoRequest;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMocks.umPeriodoUsuario;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PeriodoServiceTest {

    @InjectMocks
    private PeriodoService periodoService;
    @Mock
    private PeriodoRepository periodoRepository;
    @Mock
    private AutenticacaoService autenticacaoService;
    @Mock
    private PeriodoAlimentoDietaRepository periodoAlimentoDietaRepository;

    @Test
    public void adicionarPeriodoUsuario_deveSalvarPeriodoUsuario_quandoDadosEstiveremCorretos() {
        when(periodoRepository.existsByUsuarioIdAndDescricaoIgnoreCase(anyInt(), anyString())).thenReturn(false);
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var request = umPeriodoRequest();
        periodoService.adicionarPeriodoUsuario(request);

        verify(periodoRepository, times(1)).save(any(Periodo.class));
    }

    @Test
    public void adicionarPeriodoUsuario_deveLancarException_quandoDescricaoEstiverVazia() {
        when(periodoRepository.existsByUsuarioIdAndDescricaoIgnoreCase(anyInt(), anyString())).thenReturn(false);

        var request = umPeriodoRequest();
        request.setDescricao(null);
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.adicionarPeriodoUsuario(request))
            .withMessage("É preciso ter uma descrição para o período.");
    }

    @Test
    public void adicionarPeriodoUsuario_deveLancarException_quandoPeriodoJaExistirParaUsuario() {
        when(periodoRepository.existsByUsuarioIdAndDescricaoIgnoreCase(anyInt(), anyString())).thenReturn(true);
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var request = umPeriodoRequest();
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.adicionarPeriodoUsuario(request))
            .withMessage("O período Treino já está registrado para você.");
    }

    @Test
    public void removerPeriodoUsuario_deveRemoverPeriodoUsuario_quandoDadosEstiveremCorretos() {
        when(periodoRepository.findById(anyInt())).thenReturn(Optional.of(umPeriodoUsuario()));
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(periodoAlimentoDietaRepository.existsByPeriodoId(anyInt())).thenReturn(false);

        var response = periodoService.removerPeriodoUsuario(1);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("O período Pré-Treino foi removido com sucesso!");

        verify(periodoRepository, times(1)).deleteByIdAndUsuarioId(anyInt(), anyInt());
    }

    @Test
    public void removerPeriodoUsuario_deveLancarException_quandoPeriodoEstiverVinculadoParaUmaDieta() {
        when(periodoRepository.findById(anyInt())).thenReturn(Optional.of(umPeriodoUsuario()));
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(periodoAlimentoDietaRepository.existsByPeriodoId(anyInt())).thenReturn(true);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.removerPeriodoUsuario(1))
            .withMessage("Esse período já está vinculado a uma dieta.");
    }
}
