package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/taco/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @GetMapping("/{descricao}")
    public List<AlimentoResponse> buscarPorDescricao(@PathVariable String descricao) {
        var alimentoResponseList = new ArrayList<AlimentoResponse>();
        alimentoRepository.findByDescricaoContainingIgnoreCase(descricao)
            .forEach(alimento -> {
                try {
                    alimentoResponseList.add(AlimentoResponse.of(alimento));
                } catch (IOException erro) {
                    throw new ValidacaoException("Erro ao converter JSON: " + erro);
                }
            });
        return alimentoResponseList;
    }

    @GetMapping
    public List<Alimento> buscarPorDescricao() {
        return alimentoRepository.findAll();
    }
}
