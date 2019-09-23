package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.dto.InformacaoNutricionalCalculosResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.model.InformacaoNutricional;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.repository.InformacaoNutricionalRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformacaoNutricionalService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private InformacaoNutricionalRepository informacaoNutricionalRepository;

    public List<InformacaoNutricionalCalculosResponse> getAllInfo() {
        return informacaoNutricionalRepository
            .findByUsuario(new Usuario(usuarioService.getUsuarioAutenticado().getId()))
            .stream()
            .map(InformacaoNutricionalCalculosResponse::of)
            .collect(Collectors.toList());
    }

    public void save(InformacaoNutricional informacaoNutricional) {
        informacaoNutricional.setDataCadastroInformacao(LocalDateTime.now());
        informacaoNutricional.setDataUltimoExame(informacaoNutricional.getDataUltimoExame());
        informacaoNutricional.setUsuario(new Usuario(usuarioService.getUsuarioAutenticado().getId()));
        informacaoNutricionalRepository.save(informacaoNutricional);
    }
}
