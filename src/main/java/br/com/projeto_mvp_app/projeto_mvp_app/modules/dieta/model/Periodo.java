package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalTime;

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

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "HORA", nullable = false)
    private LocalTime hora;

    @JoinColumn(name = "FK_DIETA")
    @JsonIgnore
    @ManyToOne
    private Dieta dieta;

    public Periodo(Integer id) {
        this.id = id;
    }

    public static Periodo of(PeriodoRequest request) {
        var periodo = new Periodo();
        BeanUtils.copyProperties(request, periodo);
        periodo.setDieta(new Dieta(request.getDietaId()));
        return periodo;
    }
}
