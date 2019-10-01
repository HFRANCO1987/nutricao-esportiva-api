package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;

}
