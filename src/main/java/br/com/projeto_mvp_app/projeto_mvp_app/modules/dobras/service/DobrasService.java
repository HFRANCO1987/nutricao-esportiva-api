package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.repository.DobrasRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DobrasService {

    @Autowired
    private DobrasRepository dobrasRepository;
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Transactional
    public SuccessResponseDetails adicionarDobras(DobrasRequest request) {
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticadoId();
        var dobras = Dobras.of(request, usuarioLogadoId);
        atualizarDobrasAnterioresParaAntigas(usuarioLogadoId);
        dobrasRepository.save(dobras);
        return new SuccessResponseDetails("As dobras foram adicionadas com sucesso!");
    }

    private void atualizarDobrasAnterioresParaAntigas(Integer usuarioId) {
        dobrasRepository.atualizarDobrasAnterioresParaAntigas(EBoolean.F, usuarioId);
    }

    public DobrasResponse retornarCalculoDobras(boolean tresDobras, boolean seteDobras) {
        var dobras = dobrasRepository
            .findByUsuarioIdAndAtual(autenticacaoService.getUsuarioAutenticadoId(), EBoolean.V)
            .orElseThrow(() -> new ValidacaoException("Não existem dobras cadastradas para você."));
        return DobrasResponse.of(dobras, tresDobras, seteDobras);
    }
}
