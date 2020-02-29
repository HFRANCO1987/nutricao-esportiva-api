package br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.controller;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.response.SuccessResponseDetails;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasRequest;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.dto.DobrasResponse;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dobras.service.DobrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/dobras")
public class DobrasController {

    @Autowired
    private DobrasService dobrasService;

    @GetMapping("calculo")
    public DobrasResponse retornarCalculoDobras(@PathParam("tresDobras") boolean tresDobras,
                                                @PathParam("seteDobras") boolean seteDobras) {
        return dobrasService.retornarCalculoDobras(tresDobras, seteDobras);
    }

    @PostMapping
    public SuccessResponseDetails adicionarDobras(@RequestBody DobrasRequest request) {
        return dobrasService.adicionarDobras(request);
    }
}
