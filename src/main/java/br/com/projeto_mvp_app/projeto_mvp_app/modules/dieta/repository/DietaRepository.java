package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {

    List<Dieta> findByUsuarioId(Integer id);
}
