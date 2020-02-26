package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page buscarTodos(PageRequest pageable, AlimentoFiltros filtros) {
        return alimentoRepository
            .findAll(filtros.toPredicate().build(), pageable)
            .map(alimento -> AlimentoResponse.of(null, alimento));
    }

    public List<AlimentoResponse> buscarTodosSemPaginacao(AlimentoFiltros filtros) {
        return StreamSupport
            .stream(alimentoRepository.findAll(filtros.toPredicate().build()).spliterator(), false)
            .map(alimento -> AlimentoResponse.of(null, alimento))
            .collect(Collectors.toList());
    }

    public AlimentoResponse buscarPorId(Integer id) {
        return AlimentoResponse.of(null, alimentoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O alimento não foi encontrado."))) ;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
