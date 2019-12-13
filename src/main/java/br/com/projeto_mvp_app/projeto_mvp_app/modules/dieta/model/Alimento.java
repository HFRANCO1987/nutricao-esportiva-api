package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

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
@Table(name = "ALIMENTO")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORIA", nullable = false)
    private Categoria categoria;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "QUANTIDADE_BASE", nullable = false)
    private Integer quantidadeBase;

    @Column(name = "UNIDADE_BASE", nullable = false, length = 10)
    private String unidadeBase;

    @Column(name = "ATRIBUTOS", nullable = false, length = 3000)
    @JsonIgnore
    private String atributos;
}
