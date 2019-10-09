package br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.repository;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.dto.UsuarioAutenticado;

public interface UsuarioRepositoryCustom {

    UsuarioAutenticado findUsuarioAutenticadoByEmail(String email);
}
