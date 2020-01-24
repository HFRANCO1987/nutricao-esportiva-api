package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.predicate;

import br.com.projeto_mvp_app.projeto_mvp_app.config.PredicateBase;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums.EPeriodo;
import com.querydsl.jpa.JPAExpressions;

import java.time.LocalDateTime;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.QAlimento.alimento;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.QCategoria.categoria;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.QDieta.dieta;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.QPeriodo.periodo;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.QPeriodoAlimentoDieta.periodoAlimentoDieta;
import static org.springframework.util.ObjectUtils.isEmpty;

@SuppressWarnings("PMD.TooManyStaticImports")
public class DietaPredicate extends PredicateBase {

    public DietaPredicate comDescricao(String descricao) {
        if (!isEmpty(descricao)) {
            builder.and(dieta.descricao.containsIgnoreCase(descricao));
        }
        return this;
    }

    public DietaPredicate comDataCadastro(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (!isEmpty(dataInicio) && !isEmpty(dataFim)) {
            builder.and(dieta.dataCadastro.between(dataInicio, dataFim));
        }
        if (!isEmpty(dataInicio)) {
            builder.and(dieta.dataCadastro.between(dataInicio, LocalDateTime.now()));
        }
        return this;
    }

    public DietaPredicate comUsuario(Integer usuarioId) {
        if (!isEmpty(usuarioId)) {
            builder.and(dieta.usuario.id.eq(usuarioId));
        }
        return this;
    }

    public DietaPredicate comAlimento(String descricao) {
        if (!isEmpty(descricao)) {
            builder.and(dieta.id.in(
                JPAExpressions
                    .select(dieta.id)
                    .from(periodoAlimentoDieta)
                    .leftJoin(periodoAlimentoDieta.dieta, dieta)
                    .leftJoin(periodoAlimentoDieta.alimento, alimento)
                    .where(alimento.descricao.containsIgnoreCase(descricao))
            ));
        }
        return this;
    }

    public DietaPredicate comPeriodo(EPeriodo codigo) {
        if (!isEmpty(codigo)) {
            builder.and(dieta.id.in(
                JPAExpressions
                    .select(dieta.id)
                    .from(periodoAlimentoDieta)
                    .leftJoin(periodoAlimentoDieta.dieta, dieta)
                    .leftJoin(periodoAlimentoDieta.periodo, periodo)
                    .where(periodo.codigo.eq(codigo))
            ));
        }
        return this;
    }

    public DietaPredicate comCategoria(String descricao) {
        if (!isEmpty(descricao)) {
            builder.and(dieta.id.in(
                JPAExpressions
                    .select(dieta.id)
                    .from(periodoAlimentoDieta)
                    .leftJoin(periodoAlimentoDieta.dieta, dieta)
                    .leftJoin(periodoAlimentoDieta.alimento, alimento)
                    .leftJoin(alimento.categoria, categoria)
                    .where(categoria.descricao.containsIgnoreCase(descricao))
            ));
        }
        return this;
    }
}
