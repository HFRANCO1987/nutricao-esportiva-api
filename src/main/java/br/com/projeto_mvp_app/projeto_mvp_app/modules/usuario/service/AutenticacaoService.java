package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado.of;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.exception.UsuarioException.USUARIO_NAO_ENCONTRADO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.exception.UsuarioException.USUARIO_SEM_SESSAO;
import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class AutenticacaoService {

    private static final String ANONYMOUS_USER = "anonymousUser";

    @Autowired
    private UsuarioRepository usuarioRepository;

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

}
