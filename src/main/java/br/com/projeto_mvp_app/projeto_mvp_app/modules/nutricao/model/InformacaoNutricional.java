package br.com.projeto_mvp_app.projeto_mvp_app.modules.nutricao.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "INFORMACAO_NUTRICIONAL")
public class InformacaoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "ALTURA")
    @NotNull
    private Float altura;

    @Column(name = "PESO")
    @NotNull
    private Float peso;

    @Column(name = "HDL")
    @NotNull
    private Float hdl;

    @Column(name = "COLESTEROL_TOTAL")
    @NotNull
    private Float colesterolTotal;

    @Column(name = "TRIGLICERIDES")
    @NotNull
    private Float triglicerides;

    @Column(name = "GLICOSE")
    @NotNull
    private Float glicose;

    @Column(name = "INSULINA")
    @NotNull
    private Float insulina;

    @Column(name = "DATA_CADASTRO_INFORMACAO")
    @NotNull
    private LocalDateTime dataCadastroInformacao;

    @Column(name = "DATA_ULTIMO_EXAME")
    @NotNull
    private LocalDateTime dataUltimoExame;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    @NotNull
    private Usuario usuario;
}
