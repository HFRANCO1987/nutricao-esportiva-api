package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioRepositoryCustom {

    UsuarioAutenticado findUsuarioAutenticadoByEmail(String email);

    List<Usuario> findAllPredicate(Predicate build);

    List<Usuario> findAllPredicatePageable(Pageable pageable, Predicate predicate);
}
