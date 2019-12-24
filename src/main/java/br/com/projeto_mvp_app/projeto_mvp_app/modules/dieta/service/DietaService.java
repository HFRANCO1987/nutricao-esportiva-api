package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.DietaRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.DietaResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.PeriodoAlimentoDietaRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("PMD.UnusedPrivateField")
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private PeriodoAlimentoDietaRepository periodoAlimentoDietaRepository;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Dieta salvar(DietaRequest request) {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        return dietaRepository.save(Dieta.of(request, usuarioLogado.getId()));
    }

    @Transactional
    public void salvarAlimentosPeriodoDaDieta(List<PeriodoAlimentoDietaRequest> request) {
        if (request.isEmpty()) {
            throw new ValidacaoException("É necessário informar a dieta, os alimentos e o período.");
        }
        validarDietaCorreta(request);
        var dietaId = request.get(0).getDietaId();
        validarPermissaoDoUsuarioSobreDieta(dietaId);
        desvincularItensAtuaisDaDieta(dietaId);
        periodoAlimentoDietaRepository.saveAll(request
            .stream()
            .map(PeriodoAlimentoDieta::of)
            .collect(Collectors.toList()));
    }

    private void validarDietaCorreta(List<PeriodoAlimentoDietaRequest> request) {
        var dietaId = request.get(0).getDietaId();
        request.forEach(itensDieta -> {
            if (!itensDieta.getDietaId().equals(dietaId)) {
                throw new ValidacaoException("Todos os itens devem ser vinculados para uma única dieta.");
            }
        });
    }

    private void validarPermissaoDoUsuarioSobreDieta(Integer dietaId) {
        var usuarioLogadoId = usuarioService.getUsuarioAutenticado().getId();
        if (!dietaRepository.existsByIdAndUsuarioId(dietaId, usuarioLogadoId)) {
            throw new ValidacaoException("Você não tem permissão para alterar essa dieta.");
        }
    }

    private void desvincularItensAtuaisDaDieta(Integer dietaId) {
        if (periodoAlimentoDietaRepository.existsByDietaId(dietaId)) {
            periodoAlimentoDietaRepository.deleteByDietaId(dietaId);
        }
    }

    public DietaResponse buscarDietaComDadosCompletos(Integer id) {
        var usuarioLogadoId = usuarioService.getUsuarioAutenticado().getId();
        var dieta = dietaRepository.findByIdAndUsuarioId(id, usuarioLogadoId)
            .orElseThrow(() -> new ValidacaoException("A dieta não foi encontrada."));
        var alimentosPeriodosDaDieta = periodoAlimentoDietaRepository.findByDietaId(dieta.getId());
        return DietaResponse.of(dieta, alimentosPeriodosDaDieta);
    }

    public Page<Dieta> buscarTodas(Integer page, Integer size) {
        var usuarioLogadoId = usuarioService.getUsuarioAutenticado().getId();
        return dietaRepository.findByUsuarioId(PageRequest.of(page, size), usuarioLogadoId);
    }
}