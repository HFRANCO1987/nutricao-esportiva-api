package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.PesoAltura;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PesoAlturaRepository extends JpaRepository<PesoAltura, Integer> {

    Boolean existsByUsuarioId(Integer usuarioId);

    List<PesoAltura> findByUsuarioId(Integer id);

    Optional<PesoAltura> findTop1ByUsuarioIdAndPesoAlturaAtualOrderByDataCadastroDesc(Integer usuarioId,
                                                                                      EBoolean pesoAlturaAtual);

    @Modifying
    @Query(value = "update PesoAltura pa set pa.pesoAlturaAtual = :pesoAlturaAtual where usuario = :usuario")
    void atualizarPesoAlturaAtualByUsuarioId(EBoolean pesoAlturaAtual, Usuario usuario);
}
