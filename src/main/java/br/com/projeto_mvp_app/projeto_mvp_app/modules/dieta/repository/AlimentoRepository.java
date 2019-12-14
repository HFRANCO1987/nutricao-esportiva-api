package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Integer>,
    QuerydslPredicateExecutor<Alimento> {

    List<Alimento> findByDescricaoContainingIgnoreCase(String descricao);

    Page<Alimento> findByDescricaoContainingIgnoreCase(Pageable pageable, String descricao);
}
