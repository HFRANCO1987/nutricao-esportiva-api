package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.exceptions.messages.UsuarioExceptions.USUARIO_NAO_ENCONTRADO;
import static br.com.projeto_mvp_app.projeto_mvp_app.exceptions.messages.UsuarioExceptions.USUARIO_SEM_SESSAO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado.of;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    public void save(Usuario usuario) {
        var senha = bcryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
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

    public void atualizarUltimoAcesso() {
        usuarioRepository.findById(getUsuarioAutenticado().getId())
            .ifPresent(usuario -> {
                usuario.setUltimoAcesso(LocalDateTime.now());
                usuarioRepository.save(usuario);
            });
    }
}
