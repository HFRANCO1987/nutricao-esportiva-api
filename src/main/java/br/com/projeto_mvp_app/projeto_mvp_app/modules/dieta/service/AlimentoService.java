package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.AlimentoFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page buscarTodos(PageRequest pageable, AlimentoFiltros filtros) {
        return alimentoRepository.findAll(filtros.toPredicate().build(), pageable).map(AlimentoResponse::of);
    }

    public List<AlimentoResponse> buscarTodosSemPaginacao(AlimentoFiltros filtros) {
        var alimentos = alimentoRepository.findAll(filtros.toPredicate().build());
        var alimentosList = new ArrayList<AlimentoResponse>();
        alimentos.forEach(alimento -> {
            alimentosList.add(AlimentoResponse.of(alimento));
        });
        return alimentosList;
    }

    public AlimentoResponse buscarPorId(Integer id) {
        return AlimentoResponse.of(alimentoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O alimento não foi encontrado."))) ;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
