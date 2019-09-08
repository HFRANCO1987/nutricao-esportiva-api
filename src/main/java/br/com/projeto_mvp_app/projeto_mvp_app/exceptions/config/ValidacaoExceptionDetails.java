package br.com.projeto_mvp_app.projeto_mvp_app.exceptions.config;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {

    private String title;
    private int status;
    private String details;
    private long timestamp;

}
