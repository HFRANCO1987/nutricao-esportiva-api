package br.com.projeto_mvp_app.projeto_mvp_app.exceptions;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {

    private long timestamp;
    private int status;
    private String error;
    private String message;

}