package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.PesoAlturaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado.of;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.exception.UsuarioException.*;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario.of;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PesoAlturaRepository pesoAlturaRepository;

    public void save(UsuarioRequest usuarioRequest) {
        var usuario = of(usuarioRequest);
        validarDadosUsuario(usuario);
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuario = usuarioRepository.save(usuario);
        if (!isEmpty(usuarioRequest.getPeso()) && !isEmpty(usuarioRequest.getAltura())) {
            tratarUsuarioPeso(usuarioRequest.getPeso(), usuarioRequest.getAltura(), usuario.getId());
        }
    }

    private void validarDadosUsuario(Usuario usuario) {
        validarCpf(usuario);
        validarDataNascimento(usuario);
        validarEmailExistente(usuario);
        validarCpfExistente(usuario);
    }

    private void validarCpf(Usuario usuario) {
        if (!isEmpty(usuario.getCpf())) {
            try {
                var cpfValidator = new CPFValidator();
                cpfValidator.assertValid(usuario.getCpf());
            } catch (Exception ex) {
                throw new ValidacaoException("O CPF está inválido.");
            }
        } else {
            throw new ValidacaoException("O CPF deve ser informado.");
        }
    }

    private void validarDataNascimento(Usuario usuario) {
        if (usuario.getDataNascimento().isEqual(LocalDate.now())) {
            throw USUARIO_DATA_NASCIMENTO_IGUAL_HOJE.getException();
        }
        if (usuario.getDataNascimento().isAfter(LocalDate.now())) {
            throw USUARIO_DATA_NASCIMENTO_MAIOR_HOJE.getException();
        }
    }

    private void validarEmailExistente(Usuario usuario) {
        usuarioRepository.findByEmail(usuario.getEmail())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_EMAIL_JA_CADASTRADO.getException();
                }
            });
    }

    private void validarCpfExistente(Usuario usuario) {
        usuarioRepository.findByCpf(usuario.getCpf())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_CPF_JA_CADASTRADO.getException();
                }
            });
    }

    @Transactional
    public UsuarioAutenticado getUsuarioAutenticadoAtualizaUltimaData() {
        var usuarioAtualizado = usuarioRepository
            .findById(getUsuarioAutenticado().getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException);
        return of(atualizarUltimoAcesso(usuarioAtualizado));
    }

    @Transactional
    private Usuario atualizarUltimoAcesso(Usuario usuario) {
        usuario.setUltimoAcesso(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public UsuarioAutenticado getUsuarioAutenticado() {
        var email = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw USUARIO_SEM_SESSAO.getException();
        }
        return of(usuarioRepository.findByEmail(email).orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }

    public boolean existeUsuarioAutenticado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return !isEmpty(authentication) && !authentication.getName().equals("anonymousUser");
    }

    public Page<Usuario> getUsuarios(Integer page, Integer size, UsuarioFiltros filtros) {
        return usuarioRepository.findAll(filtros.toPredicate().build(), PageRequest.of(page, size));
    }

    public List<Usuario> getUsuariosPageableQueryDsl(Integer page, Integer size, UsuarioFiltros filtros) {
        return usuarioRepository.findAllPredicatePageable(PageRequest.of(page, size), filtros.toPredicate().build());
    }

    public UsuarioPesoAlturaResponse buscarUsuarioComHistoricoDePesoEAltura() {
        var usuarioId = getUsuarioAutenticado().getId();
        return UsuarioPesoAlturaResponse.of(usuarioRepository.findById(usuarioId)
                .orElseThrow(USUARIO_NAO_ENCONTRADO::getException),
            pesoAlturaRepository.findByUsuarioId(usuarioId));
    }

    @Transactional
    public UsuarioAnalisePesoResponse tratarUsuarioPeso(Double peso, Double altura, Integer usuarioId) {
        if (existeUsuarioAutenticado()) {
            var usuarioLogadoId = getUsuarioAutenticado().getId();
            tratarHistoricoDePesoAltura(usuarioLogadoId);
            return tratarAnalisePeso(pesoAlturaRepository.save(PesoAltura.of(peso, altura, usuarioLogadoId)));
        } else {
            pesoAlturaRepository.save(PesoAltura.of(peso, altura, usuarioId));
            return new UsuarioAnalisePesoResponse();
        }
    }

    private UsuarioAnalisePesoResponse tratarAnalisePeso(PesoAltura atual) {
        var anterior = pesoAlturaRepository.findTop1ByUsuarioIdAndPesoAlturaAtualOrderByDataCadastroDesc(
            atual.getUsuario().getId(), EBoolean.F);
        var usuario = usuarioRepository.findById(getUsuarioAutenticado().getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException);
        return anterior.map(pesoAnterior -> UsuarioAnalisePesoResponse.of(usuario, atual, pesoAnterior))
            .orElseGet(() -> UsuarioAnalisePesoResponse.of(usuario, atual, atual));
    }

    private void tratarHistoricoDePesoAltura(Integer usuarioId) {
        if (pesoAlturaRepository.existsByUsuarioId(usuarioId)) {
            pesoAlturaRepository.atualizarPesoAlturaAtualByUsuarioId(EBoolean.F, new Usuario(usuarioId));
        }
    }
}
