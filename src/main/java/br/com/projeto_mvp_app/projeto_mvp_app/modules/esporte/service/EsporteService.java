package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.Esporte;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.UsuarioEsporte;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.repository.EsporteRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.repository.UsuarioEsporteRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EsporteService {

    @Autowired
    private EsporteRepository esporteRepository;
    @Autowired
    private UsuarioEsporteRepository usuarioEsporteRepository;
    @Autowired
    private AutenticacaoService autenticacaoService;

    public List<Esporte> buscarTodos() {
        return esporteRepository.findAll();
    }

    public List<Esporte> buscarTodosEsportesDoUsuario() {
        return usuarioEsporteRepository
            .findAllByUsuarioId(autenticacaoService.getUsuarioAutenticadoId())
            .stream()
            .map(UsuarioEsporte::getEsporte)
            .collect(Collectors.toList());
    }

    @Transactional
    public SuccessResponseDetails adicionarEsporteUsuario(Integer esporteId) {
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticadoId();
        validarUsuarioComEsporte(esporteId, usuarioLogadoId);
        usuarioEsporteRepository.save(UsuarioEsporte.of(usuarioLogadoId, esporteId));
        return new SuccessResponseDetails("O esporte foi vinculado ao usuário com sucesso!");
    }

    @Transactional
    public SuccessResponseDetails removerEsporteUsuario(Integer esporteId) {
        var usuarioLogadoId = autenticacaoService.getUsuarioAutenticadoId();
        usuarioEsporteRepository.deleteByUsuarioIdAndEsporteId(usuarioLogadoId, esporteId);
        return new SuccessResponseDetails("O esporte foi removido do usuário com sucesso!");
    }

    private void validarUsuarioComEsporte(Integer esporteId, Integer usuarioId) {
        if (usuarioEsporteRepository.existsByUsuarioIdAndEsporteId(usuarioId, esporteId)) {
            throw new ValidacaoException("Esse esporte já está vinculado a este usuário.");
        }
    }

    public Esporte buscarEsportePorId(Integer id) {
        return esporteRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O esporte não existe para esse usuário."));
    }
}
