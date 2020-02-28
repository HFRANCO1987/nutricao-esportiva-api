package br.com.projeto_mvp_app.projeto_mvp_app.modules.esporte.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO_ESPORTES")
public class UsuarioEsporte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "FK_USUARIO", nullable = false)
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(name = "FK_ESPORTE", nullable = false)
    @ManyToOne
    private Esporte esporte;

    public static UsuarioEsporte of(Integer usuarioId, Integer esporteId) {
        return UsuarioEsporte
            .builder()
            .usuario(new Usuario(usuarioId))
            .esporte(new Esporte(esporteId))
            .build();
    }
}
