package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.exception;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import lombok.Getter;

public enum UsuarioException {

    USUARIO_NAO_ENCONTRADO(new ValidacaoException("Usuário não encontrado.")),
    USUARIO_SEM_SESSAO(new ValidacaoException("Não há uma sessão de usuário ativa.")),
    USUARIO_EMAIL_JA_CADASTRADO(new ValidacaoException("Email já cadastrado para um usuário ativo.")),
    USUARIO_CPF_JA_CADASTRADO(new ValidacaoException("CPF já cadastrado para um usuário ativo.")),
    USUARIO_ACESSO_INVALIDO(new ValidacaoException("Usuário ou senha inválidos, tente novamente.")),
    USUARIO_DATA_NASCIMENTO_IGUAL_HOJE(
        new ValidacaoException("A data de nascimento não pode ser igual à data de hoje.")),
    USUARIO_DATA_NASCIMENTO_MAIOR_HOJE(
        new ValidacaoException("A data de nascimento não pode ser superior à data de hoje.")),
    USUARIO_PESO_NAO_ENCONTRADO(new ValidacaoException("Não foi encontrado um peso registrado para este usuário."));

    @Getter
    private ValidacaoException exception;

    UsuarioException(ValidacaoException exception) {
        this.exception = exception;
    }
}
