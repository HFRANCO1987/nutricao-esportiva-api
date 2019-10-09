package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, UsuarioRepositoryCustom {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    @Modifying
    @Query("update Usuario a set a.ultimoAcesso = :ultimoAcesso where a.id = :id")
    void atualizarUltimoAcesso(LocalDateTime ultimoAcesso, Integer id);
}
