package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Usuario> getUsuarios(PageRequest pageable, UsuarioFiltros usuarioFiltros) {
        return usuarioService.getUsuarios(pageable, usuarioFiltros);
    }

    @GetMapping("/custom")
    public Page<UsuarioResponse> getUsuariosCustom(PageRequest pageable, UsuarioFiltros usuarioFiltros) {
        return usuarioService.getUsuariosCustom(pageable, usuarioFiltros);
    }

    @GetMapping("page")
    public List<Usuario> getUsuariosPageableQueryDsl(PageRequest pageable, UsuarioFiltros usuarioFiltros) {
        return usuarioService.getUsuariosPageableQueryDsl(pageable, usuarioFiltros);
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

    @PutMapping("atualizar-peso-altura")
    public UsuarioAnalisePesoResponse atualizarPesoAltura(@RequestParam("peso") Double peso,
                                                          @RequestParam("altura") Double altura) {
        return usuarioService.tratarUsuarioPeso(peso, altura, null);
    }

    @GetMapping("analise-peso-altura")
    public UsuarioAnalisePesoResponse consultarAnalisePesoAltura() {
        return usuarioService.consultarAnalisePesoAltura();
    }
}
