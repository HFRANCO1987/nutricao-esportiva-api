package br.com.projeto_mvp_app.projeto_mvp_app.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.PesoAlturaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.projeto_mvp_app.projeto_mvp_app.mocks.UsuarioMocks.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AutenticacaoService autenticacaoService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PesoAlturaRepository pesoAlturaRepository;

    @Test
    public void save_deveSalvarUsuario_quandoDadosEstiveremCorretosComUsuarioAutenticado() {
        when(pesoAlturaRepository
            .findTop1ByUsuarioIdAndPesoAlturaAtualOrderByDataCadastroDesc(anyInt(), any()))
            .thenReturn(Optional.of(umPesoAltura()));
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(umUsuario()));
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(umUsuario()));
        when(usuarioRepository.findByCpf(anyString())).thenReturn(Optional.of(umUsuario()));
        when(usuarioRepository.save(any())).thenReturn(umUsuario());
        when(autenticacaoService.existeUsuarioAutenticado()).thenReturn(true);
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(pesoAlturaRepository.existsByUsuarioId(anyInt())).thenReturn(true);
        when(pesoAlturaRepository.save(any())).thenReturn(umPesoAltura());

        usuarioService.save(umUsuarioRequest());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(pesoAlturaRepository, times(1)).atualizarPesoAlturaAtualByUsuarioId(any(),
            any(Usuario.class));
        verify(pesoAlturaRepository, times(1)).save(any(PesoAltura.class));
    }

    @Test
    public void save_deveSalvarUsuario_quandoDadosEstiveremCorretosSemUsuarioAutenticado() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(umUsuario()));
        when(usuarioRepository.findByCpf(anyString())).thenReturn(Optional.of(umUsuario()));
        when(usuarioRepository.save(any())).thenReturn(umUsuario());
        when(autenticacaoService.existeUsuarioAutenticado()).thenReturn(false);

        usuarioService.save(umUsuarioRequest());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(pesoAlturaRepository, times(1)).save(any(PesoAltura.class));
    }

    @Test
    public void save_deveLancarException_quandoDataNascimentoIgualHoje() {
        var request = umUsuarioRequest();
        request.setDataNascimento(LocalDate.now());

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> usuarioService.save(request))
            .withMessage("A data de nascimento não pode ser igual à data de hoje.");
    }

    @Test
    public void save_deveLancarException_quandoDataNascimentoMaiorHoje() {
        var request = umUsuarioRequest();
        request.setDataNascimento(LocalDate.now().plusDays(1));

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> usuarioService.save(request))
            .withMessage("A data de nascimento não pode ser superior à data de hoje.");
    }

    @Test
    public void save_deveLancarException_quandoCpfEstiverInvalido() {
        var request = umUsuarioRequest();
        request.setCpf("151.103.597-52");

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> usuarioService.save(request))
            .withMessage("O CPF está inválido.");
    }

    @Test
    public void save_deveLancarException_quandoCpfForNulo() {
        var request = umUsuarioRequest();
        request.setCpf(null);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> usuarioService.save(request))
            .withMessage("O CPF deve ser informado.");
    }

    @Test
    public void save_deveLancarException_quandoJaExistirCpfCadastrado() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(umUsuario()));

        var request = umUsuarioRequest();
        request.setId(2);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> usuarioService.save(request))
            .withMessage("Email já cadastrado para um usuário ativo.");
    }

    @Test
    public void save_deveLancarException_quandoJaExistirEmailCadastrado() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(usuarioRepository.findByCpf(anyString())).thenReturn(Optional.of(umUsuario()));
        when(usuarioRepository.save(any())).thenReturn(umUsuario());
        when(autenticacaoService.existeUsuarioAutenticado()).thenReturn(false);

        var request = umUsuarioRequest();
        request.setId(2);

        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> usuarioService.save(request))
            .withMessage("CPF já cadastrado para um usuário ativo.");
    }
}
