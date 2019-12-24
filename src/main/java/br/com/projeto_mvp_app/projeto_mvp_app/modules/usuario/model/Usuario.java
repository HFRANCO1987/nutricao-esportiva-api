package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.model;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.ESexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.USER;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.ESexo.FEMININO;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.ESexo.MASCULINO;
import static org.springframework.util.ObjectUtils.isEmpty;

@Entity
@Table(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NOME", nullable = false, length = 120)
    private String nome;

    @JsonIgnore
    @Column(name = "SENHA", nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "FK_PERMISSAO", nullable = false)
    private Permissao permissao;

    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "ULTIMO_ACESSO")
    private LocalDateTime ultimoAcesso;

    @Column(name = "CPF", nullable = false, length = 14)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SEXO", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ESexo sexo;

    @Transient
    public Integer getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }

    @JsonIgnore
    public boolean isMasculino() {
        return !isEmpty(sexo) && sexo.equals(MASCULINO);
    }

    @JsonIgnore
    public boolean isFeminino() {
        return !isEmpty(sexo) && sexo.equals(FEMININO);
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public static Usuario of(UsuarioRequest usuarioRequest) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequest, usuario);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuario.setPermissao(new Permissao(1, USER, "Usuário"));
        return usuario;
    }

    public static Usuario of(UsuarioAutenticado usuarioAutenticado) {
        return Usuario
            .builder()
            .id(usuarioAutenticado.getId())
            .build();
    }
}
