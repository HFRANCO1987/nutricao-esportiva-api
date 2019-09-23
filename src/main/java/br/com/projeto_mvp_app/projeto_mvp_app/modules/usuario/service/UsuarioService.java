package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.exceptions.messages.UsuarioExceptions.*;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado.of;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario.of;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    public void save(UsuarioRequest usuarioRequest) {
        validarDadosUsuario(usuarioRequest);
        var usuario = of(usuarioRequest);
        usuario.setSenha(bcryptPasswordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    private void validarDadosUsuario(UsuarioRequest usuarioRequest) {
        validarEmailExistente(usuarioRequest.getEmail());
        validarCpfExistente(usuarioRequest.getCpf());
    }

    private void validarEmailExistente(String email) {
        usuarioRepository.findByEmail(email)
            .ifPresent(usuarioExistente -> {
                throw USUARIO_EMAIL_JA_CADASTRADO.getException();
            });
    }

    private void validarCpfExistente(String cpf) {
        usuarioRepository.findByCpf(cpf)
            .ifPresent(usuarioExistente -> {
                throw USUARIO_CPF_JA_CADASTRADO.getException();
            });
    }

    @Transactional
    public UsuarioAutenticado getUsuarioAutenticadoAtualizaUltimaData() {
        var usuarioLogado = getUsuarioAutenticado();
        usuarioRepository.atualizarUltimoAcesso(LocalDateTime.now(), usuarioLogado.getId());
        return of(usuarioRepository.findById(usuarioLogado.getId()).orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
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

    public List<Usuario> getUsuarios() {
        var usuarioAutenticado = getUsuarioAutenticado();
        if (usuarioAutenticado.isAdmin()) {
            return usuarioRepository.findAll();
        }
        return List.of(usuarioRepository.findById(usuarioAutenticado.getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }
}
