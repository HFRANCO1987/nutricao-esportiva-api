package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@SuppressWarnings("PMD.UnusedPrivateField")
public class DietaService {

    private static final ValidacaoException DIETA_NAO_ENCONTRADA_EXCEPTION =
        new ValidacaoException("A dieta não foi encontrada.");

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
    public void salvarAlimentosPeriodoDaDieta(PeriodoAlimentoDietaRequest request) {
        validarDadosAlimentosPeriodosDieta(request);
        validarPermissaoDoUsuarioSobreDieta(request.getDietaId());
        desvincularItensAtuaisDaDieta(request.getDietaId());
        periodoAlimentoDietaRepository.saveAll(criarListaPeriodosAlimentosDieta(request));
    }

    private void validarDadosAlimentosPeriodosDieta(PeriodoAlimentoDietaRequest request) {
        if (isEmpty(request.getDietaId())) {
            throw new ValidacaoException("É necessário informar a dieta.");
        }
        if (isEmpty(request.getPeriodosAlimentos())) {
            throw new ValidacaoException("É necessário informar os alimentos e os períodos.");
        }
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

    private List<PeriodoAlimentoDieta> criarListaPeriodosAlimentosDieta(PeriodoAlimentoDietaRequest request) {
        return request
            .getPeriodosAlimentos()
            .stream()
            .map(periodosAlimentos -> PeriodoAlimentoDieta.of(request.getDietaId(), periodosAlimentos))
            .collect(Collectors.toList());
    }

    public DietaResponse buscarDietaComDadosCompletos(Integer id) {
        var usuarioLogadoId = usuarioService.getUsuarioAutenticado().getId();
        var dieta = dietaRepository.findByIdAndUsuarioId(id, usuarioLogadoId)
            .orElseThrow(() -> DIETA_NAO_ENCONTRADA_EXCEPTION);
        var alimentosPeriodosDaDieta = periodoAlimentoDietaRepository.findByDietaId(dieta.getId());
        return DietaResponse.of(dieta, alimentosPeriodosDaDieta);
    }

    public Page buscarTodas(PageRequest pageable, DietaFiltros filtros) {
        filtros.setUsuarioId(usuarioService.getUsuarioAutenticado().getId());
        return dietaRepository.findAll(filtros.toPredicate().build(), pageable);
    }

    public List<Dieta> buscarTodasSemPaginacao(DietaFiltros filtros) {
        filtros.setUsuarioId(usuarioService.getUsuarioAutenticado().getId());
        return StreamSupport
            .stream(dietaRepository.findAll(filtros.toPredicate().build()).spliterator(), false)
            .collect(Collectors.toList());
    }

    public List<Periodo> buscarPeriodos() {
        return periodoRepository.findAll();
    }

    public DietaCompletaResponse buscarDietaCompleta(Integer id) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        var dieta = dietaRepository.findByIdAndUsuarioId(id, usuarioAutenticado.getId());
        if (dieta.isEmpty()) {
            return new DietaCompletaResponse();
        } else {
            var dietaAtual = dieta.get();
            return DietaCompletaResponse.of(dietaAtual, criarResponseDePeriodos(dietaAtual.getId(),
                buscarPeriodos()));
        }
    }

    private List<AlimentoResponse> buscarAlimentosPorDietaEPeriodo(Integer dietaId, Integer periodoId) {
        return periodoAlimentoDietaRepository.findByDietaIdAndPeriodoId(dietaId, periodoId)
            .stream()
            .map(alimento -> AlimentoResponse.of(alimento.getAlimento()))
            .collect(Collectors.toList());
    }

    private List<PeriodosAlimentosResponse> criarResponseDePeriodos(Integer dietaId, List<Periodo> periodosDaDieta) {
        return periodosDaDieta
            .stream()
            .map(periodo -> PeriodosAlimentosResponse.of(periodo,
                buscarAlimentosPorDietaEPeriodo(dietaId, periodo.getId())))
            .collect(Collectors.toList());
    }

    public DietaCompletaResponse buscarDietaAtualCompleta() {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        var dieta = dietaRepository.findFirstByUsuarioIdOrderByDataCadastroDesc(usuarioAutenticado.getId());
        if (dieta.isEmpty()) {
            return new DietaCompletaResponse();
        } else {
            var dietaAtual = dieta.get();
            return DietaCompletaResponse.of(dietaAtual, criarResponseDePeriodos(dietaAtual.getId(),
                buscarPeriodos()));
        }
    }
}