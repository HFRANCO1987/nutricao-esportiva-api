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
