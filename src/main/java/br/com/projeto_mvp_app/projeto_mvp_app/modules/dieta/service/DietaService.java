package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto.PageRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.*;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    private AutenticacaoService autenticacaoService;

    @Transactional
    public Dieta salvar(DietaRequest request) {
        var usuarioLogado = autenticacaoService.getUsuarioAutenticado();
        return dietaRepository.save(Dieta.of(request, usuarioLogado.getId()));
    }

    @Transactional
    public SuccessResponseDetails salvarUmAlimentoPeriodoNaDieta(DietaAlimentoRequest request) {
        validarPermissaoDoUsuarioSobreDieta(request.getDietaId());
        periodoAlimentoDietaRepository.save(PeriodoAlimentoDieta.of(request));
        return new SuccessResponseDetails("O alimento foi inserido na dieta com sucesso!");
    }

    @Transactional
    public SuccessResponseDetails removerUmAlimentoPeriodoNaDieta(DietaAlimentoRequest request) {
        var usuarioLogado = autenticacaoService.getUsuarioAutenticado();
        dietaRepository.findByIdAndUsuarioId(request.getDietaId(), usuarioLogado.getId())
            .ifPresentOrElse(dieta -> {
                periodoAlimentoDietaRepository.deleteByDietaIdAndPeriodoIdAndAlimentoId(dieta.getId(),
                    request.getPeriodoId(), request.getAlimentoId());
            }, () -> { throw DIETA_NAO_ENCONTRADA_EXCEPTION; });
        return new SuccessResponseDetails("O alimento foi removido da dieta com sucesso!");
    }

    private void validarPermissaoDoUsuarioSobreDieta(Integer dietaId) {
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticado().getId();
        if (!dietaRepository.existsByIdAndUsuarioId(dietaId, usuarioLogadoId)) {
            throw new ValidacaoException("Você não tem permissão para alterar essa dieta.");
        }
    }

    public DietaResponse buscarDietaComDadosCompletos(Integer id) {
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticado().getId();
        var dieta = dietaRepository.findByIdAndUsuarioId(id, usuarioLogadoId)
            .orElseThrow(() -> DIETA_NAO_ENCONTRADA_EXCEPTION);
        var alimentosPeriodosDaDieta = periodoAlimentoDietaRepository.findByDietaId(dieta.getId());
        return DietaResponse.of(dieta, alimentosPeriodosDaDieta);
    }

    public Page buscarTodas(PageRequest pageable, DietaFiltros filtros) {
        filtros.setUsuarioId(autenticacaoService.getUsuarioAutenticado().getId());
        return dietaRepository.findAll(filtros.toPredicate().build(), pageable);
    }

    public List<Dieta> buscarTodasSemPaginacao(DietaFiltros filtros) {
        filtros.setUsuarioId(autenticacaoService.getUsuarioAutenticado().getId());
        return StreamSupport
            .stream(dietaRepository.findAll(filtros.toPredicate().build()).spliterator(), false)
            .collect(Collectors.toList());
    }

    public List<Periodo> buscarPeriodos() {
        return periodoRepository.findAll();
    }

    public DietaCompletaResponse buscarDietaCompleta(Integer id) {
        var usuarioAutenticado = autenticacaoService.getUsuarioAutenticado();
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
        var usuarioAutenticado = autenticacaoService.getUsuarioAutenticado();
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