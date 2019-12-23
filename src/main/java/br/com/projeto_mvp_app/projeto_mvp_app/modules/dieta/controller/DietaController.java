package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.DietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dieta")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @PostMapping("nova")
    public void salvarNovaDieta() {
        dietaService.salvarNovaDieta();
    }

    @PutMapping("{id}")
    public void editarDieta(@PathVariable Integer id) {
        dietaService.editarDieta(id);
    }
}
