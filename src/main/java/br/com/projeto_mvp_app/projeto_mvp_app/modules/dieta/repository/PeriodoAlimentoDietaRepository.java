package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.PeriodoAlimentoDieta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodoAlimentoDietaRepository extends JpaRepository<PeriodoAlimentoDieta, Integer> {

    List<PeriodoAlimentoDieta> findByDietaId(Integer dietaId);

    void deleteByDietaIdAndPeriodoIdAndAlimentoId(Integer dietaId,
                                                  Integer periodoId,
                                                  Integer alimentoId);

    List<PeriodoAlimentoDieta> findByDietaIdAndPeriodoId(Integer dietaId, Integer periodoId);

    Boolean existsByPeriodoId(Integer dietaId);

    void deleteByDietaId(Integer dietaId);
}
