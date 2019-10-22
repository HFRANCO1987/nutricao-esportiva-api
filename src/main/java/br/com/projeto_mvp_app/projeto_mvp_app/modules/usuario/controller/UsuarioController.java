package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final String AUTHORIZATION = "authorization";

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/check-session")
    public ResponseEntity checkSession() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/novo")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Usuário inserido com sucesso!")
     public void novoUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
    }

    @PutMapping("/alterar-acesso")
    @ResponseStatus(code = HttpStatus.OK, reason = "Usuário alterado com sucesso!")
    public void alterarDadosUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
    }

    @GetMapping("/get-token")
    public String getAuthorizationToken(@RequestHeader Map<String, String> headers) {
        return headers.get(AUTHORIZATION).replace("Bearer ", "");
    }

    @GetMapping("/usuario-autenticado")
    public UsuarioAutenticado getUsuarioAutenticado() {
        return usuarioService.getUsuarioAutenticadoAtualizaUltimaData();
    }

    @GetMapping("/usuario-autenticado-email")
    public UsuarioAutenticado findUsuarioAutenticadoByEmail() {
        return usuarioService.findUsuarioAutenticadoByEmail();
    }
}
