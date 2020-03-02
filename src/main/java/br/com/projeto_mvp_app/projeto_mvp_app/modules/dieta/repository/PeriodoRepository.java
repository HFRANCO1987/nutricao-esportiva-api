package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

    List<Periodo> findAllByDietaIdOrderByHora(Integer dietaId);

    Boolean existsByDietaIdAndDescricaoIgnoreCase(Integer usuarioId, String descricao);
}
