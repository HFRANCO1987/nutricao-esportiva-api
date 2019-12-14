package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taco/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @GetMapping("/{descricao}")
    public List<Alimento> buscarPorDescricao(@PathVariable String descricao) {
        return alimentoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    @GetMapping
    public List<Alimento> buscarPorDescricao() {
        return alimentoRepository.findAll();
    }
}
