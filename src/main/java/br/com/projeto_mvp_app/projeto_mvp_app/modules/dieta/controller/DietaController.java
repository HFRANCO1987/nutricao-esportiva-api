package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.DietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dieta")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @PostMapping
    public Dieta salvar(@RequestBody DietaRequest request) {
        return dietaService.salvar(request);
    }

    @PostMapping("alimentos-periodos")
    public SuccessResponseDetails salvarAlimentosPeriodos(@RequestBody PeriodoAlimentoDietaRequest request) {
        dietaService.salvarAlimentosPeriodoDaDieta(request);
        return new SuccessResponseDetails("Os alimentos e períodos foram adicionados à sua dieta!");
    }

    @GetMapping("{id}")
    public DietaResponse buscarDietaComDadosCompletos(@PathVariable Integer id) {
        return dietaService.buscarDietaComDadosCompletos(id);
    }

    @GetMapping
    public Page buscarTodas(PageRequest pageable, DietaFiltros filtros) {
        return dietaService.buscarTodas(pageable, filtros);
    }

    @GetMapping("all")
    public List<Dieta> buscarTodas(DietaFiltros filtros) {
        return dietaService.buscarTodasSemPaginacao(filtros);
    }

    @GetMapping("periodos")
    public List<Periodo> buscarPeriodos() {
        return dietaService.buscarPeriodos();
    }

    @GetMapping("{id}/completa")
    public DietaCompletaResponse buscarDietaCompleta(@PathVariable Integer id) {
        return dietaService.buscarDietaCompleta(id);
    }
}
