package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {

    List<Alimento> findByDescricaoContainingIgnoreCase(String descricao);
}
