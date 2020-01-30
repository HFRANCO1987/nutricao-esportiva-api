package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import lombok.Data;

@Data
public class TokenResponse {

    private static final String BEARER = "Bearer ";
    private static final String EMPTY = "";

    private String token;
    private String bearerToken;

    public TokenResponse(String token) {
        this.token = token.replace(BEARER, EMPTY);
        this.bearerToken = token;
    }
}
