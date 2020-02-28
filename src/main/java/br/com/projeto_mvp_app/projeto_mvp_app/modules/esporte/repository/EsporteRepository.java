package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsporteRepository extends JpaRepository<Esporte, Integer> {
}
