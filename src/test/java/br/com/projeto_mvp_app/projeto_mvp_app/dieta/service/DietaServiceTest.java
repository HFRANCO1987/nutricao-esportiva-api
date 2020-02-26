package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.DietaService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.DietaMocks.*;
import static br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks.PeriodoMocks.umPeriodoResponse;
import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.umUsuarioAutenticado;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DietaServiceTest {

    @InjectMocks
    private DietaService dietaService;
    @Mock
    private DietaRepository dietaRepository;
    @Mock
    private PeriodoService periodoService;
    @Mock
    private AutenticacaoService autenticacaoService;
    @Mock
    private PeriodoAlimentoDietaRepository periodoAlimentoDietaRepository;

    @Test
    public void salvar_deveSalvarNovaDieta_quandoDadosEstiveremCoretos() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(dietaRepository.findFirstByUsuarioIdOrderByDataCadastroDesc(anyInt())).thenReturn(Optional.of(umaDieta()));
        when(periodoService.buscarPeriodos()).thenReturn(List.of((umPeriodoResponse())));
        when(periodoAlimentoDietaRepository.findByDietaIdAndPeriodoId(anyInt(), anyInt()))
            .thenReturn(List.of(umPeriodoAlimentoDieta()));

        dietaService.salvar(umaDietaRequest());

        verify(dietaRepository, times(1)).save(any(Dieta.class));
    }

    @Test
    public void salvarUmAlimentoPeriodoNaDieta_deveSalvarAlimentoNaDieta_quandoDadosEstiveremCoretos() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(true);

        var response = dietaService.salvarUmAlimentoPeriodoNaDieta(umaDietaAlimentoRequest());
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("O alimento foi inserido na dieta com sucesso!");

        verify(periodoAlimentoDietaRepository, times(1)).save(any(PeriodoAlimentoDieta.class));
    }

    @Test
    public void salvarUmAlimentoPeriodoNaDieta_deveLancarException_quandoUsuarioNaoTiverPermissao() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(false);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> dietaService.salvarUmAlimentoPeriodoNaDieta(umaDietaAlimentoRequest()))
            .withMessage("Você não tem permissão para alterar essa dieta.");
        verify(periodoAlimentoDietaRepository, times(0)).save(any(PeriodoAlimentoDieta.class));
    }

    @Test
    public void removerUmAlimentoPeriodoNaDieta_deveRemoverAlimentoDaDieta_quandoDadosEstiveremCoretos() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(true);
        when(dietaRepository.findByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(Optional.of(umaDieta()));

        var response = dietaService.removerUmAlimentoPeriodoNaDieta(umaDietaAlimentoRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("O alimento foi removido da dieta com sucesso!");

        verify(periodoAlimentoDietaRepository, times(1))
            .deleteByDietaIdAndPeriodoIdAndAlimentoId(anyInt(), anyInt(), anyInt());
    }

    @Test
    public void removerUmAlimentoPeriodoNaDieta_deveLancarException_quandoUsuarioNaoTiverPermissao() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(dietaRepository.existsByIdAndUsuarioId(anyInt(), anyInt())).thenReturn(false);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> dietaService.removerUmAlimentoPeriodoNaDieta(umaDietaAlimentoRequest()))
            .withMessage("Você não tem permissão para alterar essa dieta.");

        verify(periodoAlimentoDietaRepository, times(0))
            .deleteByDietaIdAndPeriodoIdAndAlimentoId(anyInt(), anyInt(), anyInt());
    }
}
