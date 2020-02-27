package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

    List<Periodo> findAllByUsuarioId(Integer usuarioId);

    Boolean existsByUsuarioIdAndDescricaoIgnoreCase(Integer usuarioId, String descricao);

    void deleteByIdAndUsuarioId(Integer id, Integer usuarioId);
}
