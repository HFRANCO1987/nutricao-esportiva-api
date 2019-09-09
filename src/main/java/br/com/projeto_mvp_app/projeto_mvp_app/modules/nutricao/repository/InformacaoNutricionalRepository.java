package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.model.InformacaoNutricional;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformacaoNutricionalRepository extends JpaRepository<InformacaoNutricional, Integer> {

    List<InformacaoNutricional> findByUsuario(Usuario usuario);
}
