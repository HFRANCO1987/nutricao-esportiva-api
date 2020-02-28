package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/periodos")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @PostMapping
    public SuccessResponseDetails adicionarPeriodoDieta(@RequestBody PeriodoRequest request) {
        return periodoService.adicionarPeriodoDieta(request);
    }

    @DeleteMapping("remover/{id}")
    public SuccessResponseDetails removerPeriodoDieta(@PathVariable Integer id) {
        return periodoService.removerPeriodoDieta(id);
    }
}
