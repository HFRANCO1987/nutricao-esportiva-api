package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums.EPeriodo;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

    Optional<Periodo> findByCodigo(EPeriodo codigo);
}
