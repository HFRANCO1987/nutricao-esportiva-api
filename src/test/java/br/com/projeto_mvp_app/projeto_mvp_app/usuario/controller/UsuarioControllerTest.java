package br.com.projeto_mvp_app.projeto_mvp_app.usuario.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.projeto_mvp_app.projeto_mvp_app.helper.ApiMocks.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = {ADMIN})
public class UsuarioControllerTest {

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUsuarios_deveRetornarUsuarios_seEstiverAutenticado() throws Exception {
        mvc.perform(get(API_USUARIOS))
            .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void getUsuarios_deveRetornarUnauthorized_seNaoEstiverAutenticado() throws Exception {
        mvc.perform(get(API_USUARIOS))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {FORBIDDEN})
    public void getUsuarios_deveRetornarForbidden_seNaoPossuirPermissao() throws Exception {
        mvc.perform(get(API_USUARIOS))
            .andExpect(status().isForbidden());
    }
}
