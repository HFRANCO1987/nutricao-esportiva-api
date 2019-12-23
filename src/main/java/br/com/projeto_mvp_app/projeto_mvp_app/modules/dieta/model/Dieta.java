package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DIETA")
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @JoinColumn(name = "FK_USUARIO", nullable = false)
    @ManyToOne
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();
    }
}
