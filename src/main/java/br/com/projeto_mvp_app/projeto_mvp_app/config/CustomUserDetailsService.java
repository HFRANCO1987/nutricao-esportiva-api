package br.com.projeto_mvp_app.projeto_mvp_app.config;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.exceptions.messages.UsuarioExceptions.USUARIO_ACESSO_INVALIDO;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return usuarioRepository
            .findByEmail(email)
            .map(usuario -> {
                List<GrantedAuthority> permissoes = AuthorityUtils
                    .createAuthorityList("ROLE_" + usuario.getPermissao().getPermissao().name());
                return new User(
                    usuario.getEmail(),
                    encoder.encode(usuario.getSenha()),
                    permissoes);
            }).orElseThrow(USUARIO_ACESSO_INVALIDO::getException);
    }
}