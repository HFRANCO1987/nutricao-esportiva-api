package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EPermissao {

    USER(1, "Usuário"),
    ADMIN(2, "Administrador");

    @Getter
    private Integer id;
    @Getter
    private String descricao;
}
