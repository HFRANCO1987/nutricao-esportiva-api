package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taco/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping("descricao/{descricao}")
    public List<AlimentoResponse> buscarPorDescricao(@PathVariable String descricao) {
        return alimentoService.buscarPorDescricao(descricao);
    }

    @GetMapping("descricao/{descricao}/paginado")
    public Page<AlimentoResponse> buscarPorDescricaoPaginado(@PathVariable String descricao,
                                                     @RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        return alimentoService.buscarPorDescricaoPaginado(page, size, descricao);
    }

    @GetMapping("paginado")
    public Page<AlimentoResponse> buscarTodosPaginado(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        return alimentoService.buscarTodosPaginado(page, size);
    }

    @GetMapping("{id}")
    public AlimentoResponse buscarPorId(@PathVariable Integer id) {
        return alimentoService.buscarPorId(id);
    }

    @GetMapping("categorias")
    public List<Categoria> listarCategorias() {
        return alimentoService.listarCategorias();
    }
}
