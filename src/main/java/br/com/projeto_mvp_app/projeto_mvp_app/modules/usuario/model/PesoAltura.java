package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
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
@Table(name = "PESO_ALTURA")
public class PesoAltura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "PESO", nullable = false)
    private Double peso;

    @Column(name = "ALTURA", nullable = false)
    private Double altura;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "PESO_ALTURA_ATUAL", nullable = false)
    @Enumerated(EnumType.STRING)
    private EBoolean pesoAlturaAtual;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.pesoAlturaAtual = EBoolean.V;
    }

    public static PesoAltura of(Double peso, Double altura, Integer usuarioId) {
        return PesoAltura
            .builder()
            .usuario(new Usuario(usuarioId))
            .peso(peso)
            .altura(altura)
            .build();
    }
}
