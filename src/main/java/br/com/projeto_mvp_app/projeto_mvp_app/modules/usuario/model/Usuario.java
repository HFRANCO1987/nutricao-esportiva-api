package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "NOME", length = 120)
    @NotNull
    private String nome;

    @Column(name = "SENHA")
    @NotNull
    private String senha;

    @ManyToOne
    @JoinColumn(name = "FK_PERMISSAO")
    @NotNull
    private Permissao permissao;

    @Column(name = "DATA_CADASTRO")
    @NotNull
    private LocalDateTime dataCadastro;

    @Column(name = "ULTIMO_ACESSO")
    @NotNull
    private LocalDateTime ultimoAcesso;

    public Usuario(Integer id) {
        this.id = id;
    }
}
