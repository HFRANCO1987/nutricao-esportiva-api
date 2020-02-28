package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums.EPeriodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private PeriodoAlimentoDietaRepository periodoAlimentoDietaRepository;
    @Autowired
    private DietaRepository dietaRepository;

    @Transactional
    public SuccessResponseDetails adicionarPeriodoDieta(PeriodoRequest request) {
        validarDescricaoVazia(request);
        validarDietaVazia(request);
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticadoId();
        validarDietaNaoExistente(request.getDietaId(), usuarioLogadoId);
        var periodo = Periodo.of(request);
        validarPeriodoJaAdicionadoParaDieta(periodo);
        periodoRepository.save(periodo);
        return new SuccessResponseDetails("O período " + periodo.getDescricao() + " foi adicionado com sucesso!");
    }

    private void validarDescricaoVazia(PeriodoRequest request) {
        if (isEmpty(request.getDescricao())) {
            throw new ValidacaoException("É preciso ter uma descrição para o período.");
        }
    }

    private void validarDietaVazia(PeriodoRequest request) {
        if (isEmpty(request.getDietaId())) {
            throw new ValidacaoException("É preciso ter uma dieta vinculada ao período.");
        }
    }

    private void validarDietaNaoExistente(Integer dietaId, Integer usuarioId) {
        if (!dietaRepository.existsByIdAndUsuarioId(dietaId, usuarioId)) {
            throw new ValidacaoException("A dieta informada não existe.");
        }
    }

    private void validarPeriodoJaAdicionadoParaDieta(Periodo periodo) {
        if (periodoRepository.existsByDietaIdAndDescricaoIgnoreCase(
            periodo.getDieta().getId(), periodo.getDescricao())) {
            throw new ValidacaoException("O período " + periodo.getDescricao() + " já está registrado para essa dieta.");
        }
    }

    public void adicionarPeriodosPadroes(Integer dietaId) {
        periodoRepository.saveAll(montarPeriodosPadroes(dietaId));
    }

    private List<Periodo> montarPeriodosPadroes(Integer dietaId) {
        return List.of(
            criarPeriodo(dietaId, EPeriodo.MANHA),
            criarPeriodo(dietaId, EPeriodo.ALMOCO),
            criarPeriodo(dietaId, EPeriodo.TARDE),
            criarPeriodo(dietaId, EPeriodo.NOITE)
        );
    }

    private Periodo criarPeriodo(Integer dietaId, EPeriodo periodo) {
        return Periodo
            .builder()
            .descricao(periodo.getPeriodo())
            .dieta(new Dieta(dietaId))
            .hora(periodo.getHora())
            .build();
    }

    @Transactional
    public SuccessResponseDetails removerPeriodoDieta(Integer id) {
        var periodo = periodoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O período não existe."));
        validarPeriodoExistenteParaDieta(periodo.getId());
        periodoRepository.deleteById(periodo.getId());
        return new SuccessResponseDetails("O período " + periodo.getDescricao() + " foi removido com sucesso!");
    }

    private void validarPeriodoExistenteParaDieta(Integer periodoId) {
        if (periodoAlimentoDietaRepository.existsByPeriodoId(periodoId)) {
            throw new ValidacaoException("Esse período já está vinculado a uma dieta.");
        }
    }

    public List<PeriodoResponse> buscarPeriodosDaDieta(Integer dietaId) {
        return periodoRepository
            .findAllByDietaId(dietaId)
            .stream()
            .map(PeriodoResponse::of)
            .collect(Collectors.toList());
    }
}
