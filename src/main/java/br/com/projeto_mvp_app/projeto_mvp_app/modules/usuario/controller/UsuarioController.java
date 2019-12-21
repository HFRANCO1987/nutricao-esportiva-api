package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
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
    public Page<Usuario> getUsuarios(@Validated UsuarioFiltros usuarioFiltros,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size) {
        return usuarioService.getUsuarios(page, size, usuarioFiltros);
    }

    @GetMapping("page")
    public List<Usuario> getUsuariosPageableQueryDsl(@Validated UsuarioFiltros usuarioFiltros,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size) {
        return usuarioService.getUsuariosPageableQueryDsl(page, size, usuarioFiltros);
    }

    @GetMapping("/check-session")
    public String checkSession() {
        return "O usuário " + usuarioService.getUsuarioAutenticado().getNome() + " está logado.";
    }

    @PostMapping("/novo")
     public SuccessResponseDetails novoUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
        return new SuccessResponseDetails("Usuário inserido com sucesso!");
    }

    @PutMapping("/alterar-acesso")
    public SuccessResponseDetails alterarDadosUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
        return new SuccessResponseDetails("Usuário alterado com sucesso!");
    }

    @GetMapping("/get-token")
    public String getAuthorizationToken(@RequestHeader Map<String, String> headers) {
        return headers.get(AUTHORIZATION).replace("Bearer ", "");
    }

    @GetMapping("/usuario-autenticado")
    public UsuarioAutenticado getUsuarioAutenticado() {
        return usuarioService.getUsuarioAutenticadoAtualizaUltimaData();
    }

    @GetMapping("/is-authenticated")
    public boolean verificarSeEstaAutenticado() {
        return usuarioService.existeUsuarioAutenticado();
    }

    @PutMapping("{id}/atualizar-peso-altura")
    public UsuarioAnalisePesoResponse atualizarPesoAltura(@PathVariable Integer id,
                                                          @RequestParam("peso") Double peso,
                                                          @RequestParam("altura") Double altura) {
        return usuarioService.tratarUsuarioPeso(peso, altura, id);
    }

    @GetMapping("analise-peso-altura")
    public UsuarioAnalisePesoResponse consultarAnalisePesoAltura() {
        return usuarioService.consultarAnalisePesoAltura();
    }

    @GetMapping("historico-peso-altura")
    public UsuarioPesoAlturaResponse buscarUsuarioComHistoricoPesoAltura() {
        return usuarioService.buscarUsuarioComHistoricoDePesoEAltura();
    }
}
