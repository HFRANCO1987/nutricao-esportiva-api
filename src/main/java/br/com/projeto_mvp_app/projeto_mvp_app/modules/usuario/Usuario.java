package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "SENHA")
    @NotNull
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_PERMISSAO")
    @NotNull
    private Permissao permissao;

    @Column(name = "DATA_CADASTRO")
    @NotNull
    private LocalDateTime dataCadastro;

    @Column(name = "ULTIMO_ACESSO")
    @NotNull
    private LocalDateTime ultimoAcesso;
}
