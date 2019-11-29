package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final String AUTHORIZATION = "authorization";

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> getUsuarios(@Validated UsuarioFiltros usuarioFiltros,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size) {
        return usuarioService.getUsuarios(page, size, usuarioFiltros);
    }

    @GetMapping("/check-session")
    public String checkSession() {
        return "O usuário " + usuarioService.getUsuarioAutenticado().getNome() + " está logado.";
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
