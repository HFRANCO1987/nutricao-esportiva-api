package br.com.projeto_mvp_app.projeto_mvp_app.dobras.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.repository.DobrasRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.service.DobrasService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static br.com.projeto_mvp_app.projeto_mvp_app.dobras.mocks.DobrasMocks.umaDobraRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DobrasServiceTest {

    @InjectMocks
    private DobrasService dobrasService;
    @Mock
    private DobrasRepository dobrasRepository;
    @Mock
    private AutenticacaoService autenticacaoService;

    @Test
    public void adicionarDobras_deveAdicionarDobrasComSucesso_quandoDadosEstiveremCorretos() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);

        dobrasService.adicionarDobras(umaDobraRequest());

        verify(dobrasRepository, times(1))
            .atualizarDobrasAnterioresParaAntigas(any(), anyInt());
        verify(dobrasRepository, times(1))
            .save(any(Dobras.class));
    }

    @Test
    public void retornarCalculoDobras_deveLancarException_quandoNaoExistiremDobrasAtuais() {
        when(autenticacaoService.getUsuarioAutenticadoId()).thenReturn(7);
        when(dobrasRepository.findByUsuarioIdAndAtual(anyInt(), any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> dobrasService.retornarCalculoDobras(true, false))
            .withMessage("Não existem dobras cadastradas para você.");
    }
}