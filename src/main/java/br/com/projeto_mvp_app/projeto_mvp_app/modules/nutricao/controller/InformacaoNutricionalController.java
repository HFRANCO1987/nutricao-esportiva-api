package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.dto.InformacaoNutricionalCalculosResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.model.InformacaoNutricional;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.service.InformacaoNutricionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/informacoes-nutricionais")
public class InformacaoNutricionalController {

    @Autowired
    private InformacaoNutricionalService informacaoNutricionalService;

    @GetMapping
    public List<InformacaoNutricionalCalculosResponse> getAllInfo() {
        return informacaoNutricionalService.getAllInfo();
    }

    @PostMapping
    public void save(@RequestBody InformacaoNutricional informacaoNutricional) {
        informacaoNutricionalService.save(informacaoNutricional);
    }
}
