package br.com.projeto_mvp_app.projeto_mvp_app.modules;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.List.of;

@RestController
@RequestMapping("api")
public class ControllerTest {

    @GetMapping("sem-auth")
    public List<String> testeControllerSemAuth() {
        return of("Item 1", "Item 2", "Item 3");
    }

    @GetMapping("com-auth")
    public List<String> testeControllerComAuth() {
        return of("Item 1", "Item 2", "Item 3");
    }
}
