package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import lombok.Data;

@Data
public class UsuarioUltimoPesoResponse {

    private Integer usuarioId;
    private String usuarioNome;
    private String mensagem;

    public UsuarioUltimoPesoResponse(Integer usuarioId, String usuarioNome) {
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
    }
}
