package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.QPermissao.permissao1;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.QUsuario.usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

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
}