package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.Esporte;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.service.EsporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/esportes")
public class EsporteController {

    @Autowired
    private EsporteService esporteService;

    @GetMapping
    public List<Esporte> buscarTodos() {
        return esporteService.buscarTodos();
    }

    @GetMapping("usuario")
    public List<Esporte> buscarTodosEsportesDoUsuario() {
        return esporteService.buscarTodosEsportesDoUsuario();
    }

    @GetMapping("{id}")
    public Esporte buscarEsportePorId(@PathVariable Integer id) {
        return esporteService.buscarEsportePorId(id);
    }

    @PostMapping("{esporteId}/usuario/salvar")
    public SuccessResponseDetails adicionarEsporteUsuario(@PathVariable Integer esporteId) {
        return esporteService.adicionarEsporteUsuario(esporteId);
    }

    @DeleteMapping("{esporteId}/usuario/remover")
    public SuccessResponseDetails removereEsporteUsuario(@PathVariable Integer esporteId) {
        return esporteService.removerEsporteUsuario(esporteId);
    }
}
