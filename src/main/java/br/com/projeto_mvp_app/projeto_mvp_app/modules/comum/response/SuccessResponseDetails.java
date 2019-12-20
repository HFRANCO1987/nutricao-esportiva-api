package br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Data
public class SuccessResponseDetails {

    private Integer status;
    private HttpStatus statusCode;
    private String message;

    public SuccessResponseDetails(String message) {
        this.message = message;
        this.status = OK.value();
        this.statusCode = OK;
    }
}
