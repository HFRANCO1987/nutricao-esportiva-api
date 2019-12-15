package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.ESexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private Integer id;
    private String nome;
    private String email;
    @CPF
    private String cpf;
    private String senha;
    private LocalDate dataNascimento;
    private ESexo sexo;
    private Double peso;
    private Double altura;
}
