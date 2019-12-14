package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<AlimentoResponse> buscarPorDescricao(String descricao) {
        return alimentoRepository
            .findByDescricaoContainingIgnoreCase(descricao)
            .stream()
            .map(AlimentoResponse::of)
            .collect(Collectors.toList());
    }

    public Page<AlimentoResponse> buscarPorDescricaoPaginado(Integer page, Integer size, String descricao) {
        return alimentoRepository
            .findByDescricaoContainingIgnoreCase(PageRequest.of(page, size), descricao)
            .map(AlimentoResponse::of);
    }

    public Page<AlimentoResponse> buscarTodosPaginado(Integer page, Integer size) {
        return alimentoRepository.findAll(PageRequest.of(page, size)).map(AlimentoResponse::of);
    }

    public AlimentoResponse buscarPorId(Integer id) {
        return AlimentoResponse.of(alimentoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O alimento não foi encontrado."))) ;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
