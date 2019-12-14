package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.QPermissao.permissao1;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.QUsuario.usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @Autowired
    private EntityManager entityManager;
    protected Querydsl querydsl;

    @Override
    public UsuarioAutenticado findUsuarioAutenticadoByEmail(String email) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(
                    UsuarioAutenticado.class,
                    usuario.id,
                    usuario.nome,
                    usuario.email,
                    usuario.cpf,
                    permissao1.permissao,
                    permissao1.descricao,
                    usuario.ultimoAcesso))
            .from(usuario)
            .leftJoin(usuario.permissao, permissao1)
            .where(usuario.email.eq(email))
            .fetchOne();
    }

    @Override
    public List<Usuario> findAllPredicate(Predicate predicate) {
        return new JPAQuery<Void>(entityManager)
            .select(usuario)
            .from(usuario)
            .where(predicate)
            .fetch();
    }

    @Override
    public List<Usuario> findAllPredicatePageable(Pageable pageable, Predicate predicate) {
        var path = SimpleEntityPathResolver.INSTANCE.createPath(Usuario.class);
        var querydsl = new Querydsl(entityManager, new PathBuilder<Usuario>(path.getType(), path.getMetadata()));
        return querydsl.applyPagination(
            pageable,
            new JPAQuery<Void>(entityManager)
                .select(usuario)
                .from(usuario)
                .where(predicate)
        ).fetch();
    }
}