package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.PesoAlturaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean.V;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado.of;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.exception.UsuarioException.*;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario.of;
import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
@SuppressWarnings("PMD.TooManyStaticImports")
public class UsuarioService {

    private static final String ANONYMOUS_USER = "anonymousUser";
    private static final String FORMATO_DATA = "dd/MM/yyyy";
    private static final Integer UMA_SEMANA = 1;

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
        return !isEmpty(authentication) && !authentication.getName().equals(ANONYMOUS_USER);
    }

    public Page<Usuario> getUsuarios(PageRequest pageable, UsuarioFiltros filtros) {
        return usuarioRepository.findAll(filtros.toPredicate().build(), pageable);
    }

    public UsuarioPesoAlturaResponse buscarUsuarioComHistoricoDePesoEAltura() {
        var usuarioId = getUsuarioAutenticado().getId();
        return UsuarioPesoAlturaResponse.of(usuarioRepository.findById(usuarioId)
                .orElseThrow(USUARIO_NAO_ENCONTRADO::getException), retornarHistoricoPorUsuarioId(usuarioId));
    }

    public List<PesoAltura> retornarHistoricoPorUsuarioId(Integer usuarioId) {
        return pesoAlturaRepository.findByUsuarioIdOrderByDataCadastroDesc(usuarioId);
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
        var historico = buscarUsuarioComHistoricoDePesoEAltura();
        var analise = buscarAnalisePesoAltura();
        return anterior.map(pesoAnterior -> UsuarioAnalisePesoResponse.of(usuario, atual, pesoAnterior, historico, analise))
            .orElseGet(() -> UsuarioAnalisePesoResponse.of(usuario, atual, atual, historico, analise));
    }

    private void tratarHistoricoDePesoAltura(Integer usuarioId) {
        if (pesoAlturaRepository.existsByUsuarioId(usuarioId)) {
            pesoAlturaRepository.atualizarPesoAlturaAtualByUsuarioId(EBoolean.F, new Usuario(usuarioId));
        }
    }

    public UsuarioAnalisePesoResponse consultarAnalisePesoAltura() {
        return tratarAnalisePeso(pesoAlturaRepository.findByUsuarioIdAndPesoAlturaAtual(getUsuarioAutenticado()
            .getId(), V).orElseThrow(() -> new ValidacaoException("O peso atual não foi encontrado.")));
    }

    public UsuarioUltimoPesoResponse verificarPesagemNaUltimaSemana() {
        var usuarioLogado = getUsuarioAutenticado();
        var response = new UsuarioUltimoPesoResponse(usuarioLogado.getId(), usuarioLogado.getNome());
        var peso = pesoAlturaRepository
            .findTop1ByUsuarioIdAndPesoAlturaAtualOrderByDataCadastroDesc(usuarioLogado.getId(), V)
            .orElseThrow(USUARIO_PESO_NAO_ENCONTRADO::getException);
        if (ChronoUnit.WEEKS.between(peso.getDataCadastro(), LocalDateTime.now()) >= UMA_SEMANA) {
            response.setMensagem("Você não se pesa há mais de uma semana, vamos se pesar?");
        } else {
            response.setMensagem("A última vez que você se pesou foi em "
                + peso.getDataCadastro().toLocalDate().format(DateTimeFormatter.ofPattern(FORMATO_DATA))
                + ", e seu peso era " + peso.getPeso() + "kg.");
        }
        return response;
    }

    public List<AnalisePesoAlturaResponse> buscarAnalisePesoAltura() {
        var usuarioLogado = getUsuarioAutenticado();
        var historico = retornarHistoricoPorUsuarioId(usuarioLogado.getId());
        return IntStream
            .range(0, historico.size())
            .mapToObj(i -> i == historico.size() - 1
                ? AnalisePesoAlturaResponse.of(historico.get(i), null, true)
                : !isEmpty(historico.get(i + 1))
                ? AnalisePesoAlturaResponse.of(historico.get(i), historico.get(i + 1), false)
                : null)
            .collect(Collectors.toList());
    }
}
