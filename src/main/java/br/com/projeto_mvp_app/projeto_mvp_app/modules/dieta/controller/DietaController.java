package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.DietaRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.DietaResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.PeriodoAlimentoDietaRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
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
    public SuccessResponseDetails salvarAlimentosPeriodos(@RequestBody List<PeriodoAlimentoDietaRequest> request) {
        dietaService.salvarAlimentosPeriodoDaDieta(request);
        return new SuccessResponseDetails("Os alimentos e períodos foram adicionados à sua dieta!");
    }

    @GetMapping("{id}")
    public DietaResponse buscarDietaComDadosCompletos(@PathVariable Integer id) {
        return dietaService.buscarDietaComDadosCompletos(id);
    }

    @GetMapping
    public Page<Dieta> buscarTodas(@RequestParam("page") Integer page,
                                   @RequestParam("size") Integer size) {
        return dietaService.buscarTodas(page, size);
    }
}
