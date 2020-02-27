package br.com.projeto_mvp_app.projeto_mvp_app.dieta.mocks;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoInformacoes;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Categoria;

import java.math.BigDecimal;

public class AlimentoMocks {

    public static Alimento umAlimento() {
        return Alimento
            .builder()
            .id(1)
            .descricao("Arroz")
            .categoria(umaCategoriaAlimento())
            .calcio_mg(15.5)
            .carboidrato_g(165.0)
            .cinzas_g(15.3)
            .colesterol_mg(12.46)
            .energia_kj(56.3)
            .cobre_mg(13.2)
            .energia_kcal(123.3)
            .ferro_mg(12.2)
            .fibra_alimentar_g(15.3)
            .manganes_mg(12.46)
            .fosforo_mg(56.3)
            .potassio_mg(13.2)
            .protei_g(123.3)
            .riboflavi_mg(12.2)
            .build();
    }

    public static AlimentoResponse umAlimentoResponse() {
        return AlimentoResponse
            .builder()
            .id(1)
            .categoria("Cereais")
            .imagem("arroz")
            .descricao("Arroz")
            .quantidade(BigDecimal.valueOf(150.3))
            .informacoes(umAlimentoInformacoes())
            .build();
    }

    public static AlimentoInformacoes umAlimentoInformacoes() {
        return AlimentoInformacoes
            .builder()
            .calcio_mg(15.5)
            .carboidrato_g(165.0)
            .cinzas_g(15.3)
            .colesterol_mg(12.46)
            .energia_kj(56.3)
            .cobre_mg(13.2)
            .energia_kcal(123.3)
            .ferro_mg(12.2)
            .fibra_alimentar_g(15.3)
            .manganes_mg(12.46)
            .fosforo_mg(56.3)
            .potassio_mg(13.2)
            .protei_g(123.3)
            .riboflavi_mg(12.2)
            .build();
    }

    public static Categoria umaCategoriaAlimento() {
        return Categoria
            .builder()
            .id(1)
            .descricao("Cereais")
            .build();
    }
}
