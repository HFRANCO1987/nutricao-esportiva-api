package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model.Dobras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DobrasRepository extends JpaRepository<Dobras, Integer> {

    Optional<Dobras> findByUsuarioIdAndAtual(Integer usuarioId, EBoolean atual);

    @Modifying
    @Query("update Dobras d set d.atual = :atual where d.usuario.id = :usuarioId")
    void atualizarDobrasAnterioresParaAntigas(EBoolean atual, Integer usuarioId);
}
