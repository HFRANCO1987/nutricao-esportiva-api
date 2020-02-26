package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.DietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.PeriodoAlimentoDietaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.DietaService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.PeriodoService;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.service.AutenticacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(DietaService.class)
@Sql(scripts = {"classpath:/usuarios-test.sql", "classpath:/periodos-test.sql", "classpath:/alimentos-test.sql",
    "classpath:/dietas-test.sql"})
@DataJpaTest
public class DietaServiceIntegrationTest {

    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private PeriodoAlimentoDietaRepository periodoAlimentoDietaRepository;
    @MockBean
    private AutenticacaoService autenticacaoService;
    @MockBean
    private PeriodoService periodoService;

    @Test
    public void test1() {
        var dietas = dietaRepository.findAll();
        var alimentos = alimentoRepository.findAll();
        var periodosAlimentos = periodoAlimentoDietaRepository.findAll();
        System.out.println(dietas);
        System.out.println(alimentos);
        System.out.println(periodosAlimentos);
    }
}