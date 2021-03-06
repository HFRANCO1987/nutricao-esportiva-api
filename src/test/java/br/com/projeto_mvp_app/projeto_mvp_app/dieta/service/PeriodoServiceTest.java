package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
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
    @Mock
    private DietaRepository dietaRepository;

    @Test
    public void adicionarPeriodoDieta_deveSalvarPeriodoUsuario_quandoDadosEstiveremCorretos() {
        when(periodoRepository.existsByDietaIdAndDescricaoIgnoreCase(anyInt(), anyString())).thenReturn(false);
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(true);
        when(periodoRepository.save(any(Periodo.class))).thenReturn(umPeriodoUsuario());

        var request = umPeriodoRequest();
        var response = periodoService.adicionarPeriodoDieta(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(5);
        assertThat(response.getDescricao()).isEqualTo("Pré-Treino");
        assertThat(response.getHora()).isEqualTo(LocalTime.parse("20:00"));

        verify(periodoRepository, times(1)).save(any(Periodo.class));
    }

    @Test
    public void adicionarPeriodoDieta_deveLancarException_quandoDescricaoEstiverVazia() {
        when(periodoRepository.existsByDietaIdAndDescricaoIgnoreCase(anyInt(), anyString())).thenReturn(false);

        var request = umPeriodoRequest();
        request.setDescricao(null);
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.adicionarPeriodoDieta(request))
            .withMessage("É preciso ter uma descrição para o período.");
    }

    @Test
    public void adicionarPeriodoDieta_deveLancarException_quandoPeriodoJaExistirParaUsuario() {
        when(periodoRepository.existsByDietaIdAndDescricaoIgnoreCase(anyInt(), anyString())).thenReturn(true);
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(true);

        var request = umPeriodoRequest();
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.adicionarPeriodoDieta(request))
            .withMessage("O período Treino já está registrado para essa dieta.");
    }

    @Test
    public void removerPeriodoDieta_deveremoverPeriodoDieta_quandoDadosEstiveremCorretos() {
        when(periodoRepository.findById(anyInt())).thenReturn(Optional.of(umPeriodoUsuario()));
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(periodoAlimentoDietaRepository.existsByPeriodoId(anyInt())).thenReturn(false);
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(true);

        var response = periodoService.removerPeriodoDieta(1);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("O período Pré-Treino foi removido com sucesso!");

        verify(periodoRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void removerPeriodoDieta_deveLancarException_quandoPeriodoEstiverVinculadoParaUmaDieta() {
        when(periodoRepository.findById(anyInt())).thenReturn(Optional.of(umPeriodoUsuario()));
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(periodoAlimentoDietaRepository.existsByPeriodoId(anyInt())).thenReturn(true);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> periodoService.removerPeriodoDieta(1))
            .withMessage("Esse período já está vinculado a uma dieta.");
    }
}
