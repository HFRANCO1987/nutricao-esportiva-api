package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model.UsuarioEsporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioEsporteRepository extends JpaRepository<UsuarioEsporte, Integer> {

    List<UsuarioEsporte> findAllByUsuarioId(Integer usuarioId);

    Boolean existsByUsuarioIdAndEsporteId(Integer usuarioId, Integer esporteId);

    void deleteByUsuarioIdAndEsporteId(Integer usuarioId, Integer esporteId);
}
