package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PERMISSAO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PERMISSAO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EPermissao permissao;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;

}
