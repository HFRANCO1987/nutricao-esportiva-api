package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/periodos")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @PostMapping("usuario")
    public SuccessResponseDetails adicionarPeriodoUsuario(@RequestBody PeriodoRequest request) {
        return periodoService.adicionarPeriodoUsuario(request);
    }

    @PostMapping("padrao")
    public SuccessResponseDetails adicionarPeriodoPadrao(@RequestBody PeriodoRequest request) {
        return periodoService.adicionarPeriodoPadrao(request);
    }
}
