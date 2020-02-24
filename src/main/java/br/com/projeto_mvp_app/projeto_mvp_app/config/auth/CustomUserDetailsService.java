package br.com.projeto_mvp_app.projeto_mvp_app.config.auth;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.exception.UsuarioException.USUARIO_ACESSO_INVALIDO;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ValidacaoException {

        return usuarioRepository
            .findByEmail(email)
            .map(usuario -> new User(
                usuario.getEmail(),
                usuario.getSenha(),
                getPermissoes(usuario)))
            .orElseThrow(USUARIO_ACESSO_INVALIDO::getException);
    }

    private List<SimpleGrantedAuthority> getPermissoes(Usuario usuario) {
        return usuario
            .getPermissoes()
            .stream()
            .map(permissao -> "ROLE_" + permissao.getPermissao())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }
}