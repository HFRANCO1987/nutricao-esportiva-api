package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("MemberName")
public class AlimentoInformacoes {

    private Double umidade;
    private Double energia_kcal;
    private Double energia_kj;
    private Double protei_g;
    private Double lipideos_g;
    private Double colesterol_mg;
    private Double carboidrato_g;
    private Double fibra_alimentar_g;
    private Double cinzas_g;
    private Double calcio_mg;
    private Double magnesio_mg;
    private Integer numero_alimento;
    private Double manganes_mg;
    private Double fosforo_mg;
    private Double ferro_mg;
    private Double sodio_mg;
    private Double potassio_mg;
    private Double cobre_mg;
    private Double zinco_mg;
    private Double retinol_mcg;
    private Double re_mcg;
    private Double era_mcg;
    private Double tiami_mg;
    private Double riboflavi_mg;
    private Double piridoxi_mg;
    private Double niaci_mg;
    private Double vitami_c_mg;

    public static AlimentoInformacoes of(Alimento alimento) {
        var info = new AlimentoInformacoes();
        BeanUtils.copyProperties(alimento, info);
        return info;
    }
}
