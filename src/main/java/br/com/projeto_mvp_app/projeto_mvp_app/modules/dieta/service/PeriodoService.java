package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
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

    @Transactional
    public SuccessResponseDetails adicionarPeriodoUsuario(PeriodoRequest request) {
        validarDescricaoVazia(request);
        var periodo = Periodo.of(request);
        periodo.setUsuario(new Usuario(autenticacaoService.getUsuarioAutenticadoId()));
        validarPeriodoJaAdicionadoParaUsuario(periodo);
        periodoRepository.save(periodo);
        return new SuccessResponseDetails("O período " + periodo.getDescricao() + " foi adicionado com sucesso!");
    }

    private void validarDescricaoVazia(PeriodoRequest request) {
        if (isEmpty(request.getDescricao())) {
            throw new ValidacaoException("É preciso ter uma descrição para o período.");
        }
    }

    private void validarPeriodoJaAdicionadoParaUsuario(Periodo periodo) {
        if (periodoRepository.existsByUsuarioIdAndDescricaoIgnoreCase(
            periodo.getUsuario().getId(), periodo.getDescricao())) {
            throw new ValidacaoException("O período " + periodo.getDescricao() + " já está registrado para você.");
        }
    }

    @Transactional
    public SuccessResponseDetails adicionarPeriodoPadrao(PeriodoRequest request) {
        validarDescricaoVazia(request);
        var periodo = Periodo.of(request);
        periodo.setPadrao(EBoolean.V);
        validarPeriodoPadraoJaAdicionado(periodo);
        periodoRepository.save(periodo);
        return new SuccessResponseDetails("O período " + periodo.getDescricao() + " foi adicionado com sucesso!");
    }

    private void validarPeriodoPadraoJaAdicionado(Periodo periodo) {
        if (periodoRepository.existsByPadraoAndDescricaoIgnoreCase(EBoolean.V, periodo.getDescricao())) {
            throw new ValidacaoException("O período " + periodo.getDescricao() + " já existe.");
        }
    }

    @Transactional
    public SuccessResponseDetails removerPeriodoUsuario(Integer id) {
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticadoId();
        var periodo = periodoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O período não existe."));
        validarTentativaDeRemoverPeriodoPadrao(periodo);
        validarPeriodoExistenteParaDieta(periodo.getId());
        periodoRepository.deleteByIdAndUsuarioId(periodo.getId(), usuarioLogadoId);
        return new SuccessResponseDetails("O período " + periodo.getDescricao() + " foi removido com sucesso!");
    }

    private void validarTentativaDeRemoverPeriodoPadrao(Periodo periodo) {
        if (periodo.isPadrao()) {
            throw new ValidacaoException("Não é possível remover um período padrão.");
        }
    }

    private void validarPeriodoExistenteParaDieta(Integer periodoId) {
        if (periodoAlimentoDietaRepository.existsByPeriodoId(periodoId)) {
            throw new ValidacaoException("Esse período já está vinculado a uma dieta.");
        }
    }

    public List<PeriodoResponse> buscarPeriodosPadroes() {
        return periodoRepository
            .findAllByPadrao(EBoolean.V)
            .stream()
            .map(PeriodoResponse::of)
            .collect(Collectors.toList());
    }

    public List<PeriodoResponse> buscarPeriodosDoUsuario() {
        return periodoRepository
            .findAllByUsuarioId(autenticacaoService.getUsuarioAutenticadoId())
            .stream()
            .map(PeriodoResponse::of)
            .collect(Collectors.toList());
    }

    public List<PeriodoResponse> buscarPeriodos() {
        var periodosPadroes = buscarPeriodosPadroes();
        periodosPadroes.addAll(buscarPeriodosDoUsuario());
        return periodosPadroes;
    }
}
