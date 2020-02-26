package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taco/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public Page buscarAlimentos(PageRequest pageable, AlimentoFiltros filtros) {
        return alimentoService.buscarTodos(pageable, filtros);
    }

    @GetMapping("/all")
    public List<AlimentoResponse> buscarAlimentosSemPaginacao(AlimentoFiltros filtros) {
        return alimentoService.buscarTodosSemPaginacao(filtros);
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
