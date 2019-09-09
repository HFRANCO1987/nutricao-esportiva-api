package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.dto.InformacaoNutricionalCalculosResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.service.InformacaoNutricionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/informacoes-nutricionais")
public class InformacaoNutricionalController {

    @Autowired
    private InformacaoNutricionalService informacaoNutricionalService;

    @GetMapping
    private List<InformacaoNutricionalCalculosResponse> getAllInfo() {
        return informacaoNutricionalService.getAllInfo();
    }
}
