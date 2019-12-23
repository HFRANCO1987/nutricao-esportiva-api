package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void salvarNovaDieta() {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        dietaRepository.save(Dieta.builder().usuario(new Usuario(usuarioLogado.getId())).build());
    }
}
