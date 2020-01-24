package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.DietaRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.springframework.util.ObjectUtils.isEmpty;

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
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dataCadastro;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @JoinColumn(name = "FK_USUARIO", nullable = false)
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();
    }

    public Dieta(Integer id) {
        this.id = id;
    }

    public static Dieta of(DietaRequest request, Integer usuarioId) {
        return Dieta
            .builder()
            .id(isEmpty(request.getId()) ? null : request.getId())
            .descricao(request.getDescricao())
            .usuario(new Usuario(usuarioId))
            .build();
    }
}
