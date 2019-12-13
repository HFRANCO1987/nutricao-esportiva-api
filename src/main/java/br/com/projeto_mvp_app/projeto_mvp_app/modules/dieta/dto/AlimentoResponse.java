package br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.model.Alimento;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

@Data
public class AlimentoResponse {

    private Alimento alimento;
    private Object atributos;

    public static AlimentoResponse of(Alimento alimento) throws IOException {
        var response = new AlimentoResponse();
        response.setAlimento(alimento);
        response.setAtributos(getAtributosJson(alimento.getAtributos()));
        return response;
    }

    private static Object getAtributosJson(String json) throws IOException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Object.class);
    }
}
