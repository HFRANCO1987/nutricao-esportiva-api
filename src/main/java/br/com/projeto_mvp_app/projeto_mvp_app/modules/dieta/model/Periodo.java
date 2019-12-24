package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.enums.EPeriodo;
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
@Table(name = "PERIODO")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPeriodo codigo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    public Periodo(Integer id) {
        this.id = id;
    }
}
