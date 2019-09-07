package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
