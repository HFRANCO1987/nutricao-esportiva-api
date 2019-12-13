package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
