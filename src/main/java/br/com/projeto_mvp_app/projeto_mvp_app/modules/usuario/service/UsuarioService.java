package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
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
}
