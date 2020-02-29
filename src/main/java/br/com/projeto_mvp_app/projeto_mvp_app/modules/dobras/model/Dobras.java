package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DOBRAS")
public class Dobras {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "PEITO", nullable = false)
    private Double peito;

    @Column(name = "AXILA")
    private Double axila;

    @Column(name = "TRICEP")
    private Double tricep;

    @Column(name = "SUBESCAPULAR")
    private Double subescapular;

    @Column(name = "ABDOMINAL", nullable = false)
    private Double abdominal;

    @Column(name = "SUPRAILIACA")
    private Double suprailiaca;

    @Column(name = "COXA", nullable = false)
    private Double coxa;

    @Column(name = "ATUAL", nullable = false)
    @Enumerated(EnumType.STRING)
    private EBoolean atual;

    @Column(name = "DATA_CADASTRO")
    private LocalDateTime dataCadastro;

    @JoinColumn(name = "FK_USUARIO", nullable = false)
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();
        atual = EBoolean.V;
    }

    public static Dobras of(DobrasRequest request, Integer usuarioid) {
        var dobras = new Dobras();
        BeanUtils.copyProperties(request, dobras);
        dobras.setUsuario(new Usuario(usuarioid));
        return dobras;
    }
}
