package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMock.umPeriodoRequest;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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

    @Test
    public void adicionarPeriodoPadrao_deveSalvarPeriodoPadrao_quandoDadosEstiveremCorretos() {
        when(periodoRepository.existsByPadraoAndDescricaoIgnoreCase(any(), anyString())).thenReturn(false);

        var request = umPeriodoRequest();
        periodoService.adicionarPeriodoPadrao(request);

        verify(periodoRepository, times(1)).save(any(Periodo.class));
    }

    @Test
    public void adicionarPeriodoPadrao_deveLancarException_quandoDescricaoEstiverVazia() {
        when(periodoRepository.existsByPadraoAndDescricaoIgnoreCase(any(), anyString())).thenReturn(false);

        var request = umPeriodoRequest();
        request.setDescricao(null);
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.adicionarPeriodoPadrao(request))
            .withMessage("É preciso ter uma descrição para o período.");
    }

    @Test
    public void adicionarPeriodoPadrao_deveLancarException_quandoPeriodoJaExistirParaUsuario() {
        when(periodoRepository.existsByPadraoAndDescricaoIgnoreCase(any(), anyString())).thenReturn(true);
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        var request = umPeriodoRequest();
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.adicionarPeriodoPadrao(request))
            .withMessage("O período Treino já existe.");
    }

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
        when(periodoRepository.existsByPadraoAndDescricaoIgnoreCase(any(), anyString())).thenReturn(false);

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
}
