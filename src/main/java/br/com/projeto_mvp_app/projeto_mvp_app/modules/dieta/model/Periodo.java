package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.enums.EBoolean;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.periodo.PeriodoRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

import static org.springframework.util.ObjectUtils.isEmpty;

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

    @Column(name = "PADRAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private EBoolean padrao;

    @JoinColumn(name = "FK_USUARIO")
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @JsonIgnore
    public boolean isPadrao() {
        return !isEmpty(padrao) && padrao.equals(EBoolean.V);
    }

    public Periodo(Integer id) {
        this.id = id;
    }

    public static Periodo of(PeriodoRequest request) {
        var periodo = new Periodo();
        BeanUtils.copyProperties(request, periodo);
        periodo.setPadrao(EBoolean.F);
        return periodo;
    }
}
