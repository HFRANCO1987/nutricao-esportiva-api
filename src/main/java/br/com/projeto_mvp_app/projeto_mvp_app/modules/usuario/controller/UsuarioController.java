package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository.UsuarioRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public void save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
    }

    @GetMapping("/usuario-autenticado")
    public UsuarioAutenticado getUsuarioAutenticado() {
        return usuarioService.getUsuarioAutenticado();
    }

    @PutMapping("/atualizar-ultimo-acesso")
    public void atualizarUltimoAcesso() {
        usuarioService.atualizarUltimoAcesso();
    }
}
