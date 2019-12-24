package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Dieta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {

    Page<Dieta> findByUsuarioId(Pageable pageable, Integer id);

    Optional<Dieta> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    Boolean existsByIdAndUsuarioId(Integer id, Integer usuarioId);
}
