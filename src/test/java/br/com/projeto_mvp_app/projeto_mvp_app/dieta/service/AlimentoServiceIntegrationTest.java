package br.com.projeto_mvp_app.projeto_mvp_app.dieta.service;

import br.com.projeto_mvp_app.projeto_mvp_app.config.exception.ValidacaoException;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.dto.alimento.AlimentoFiltros;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.AlimentoRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.repository.CategoriaRepository;
import br.com.projeto_mvp_app.projeto_mvp_app.modules.dieta.service.AlimentoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.projeto_mvp_app.projeto_mvp_app.usuario.mocks.UsuarioMocks.getPage;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(AlimentoService.class)
@Sql(scripts = {"classpath:/alimentos-test.sql"})
@DataJpaTest
public class AlimentoServiceIntegrationTest {

    @Autowired
    private AlimentoService alimentoService;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void listarCategorias_deveListarTodasAsCategorias_quandoSolicitado() {
        assertThat(alimentoService.listarCategorias())
            .hasSize(3)
            .extracting("id", "descricao")
            .containsExactlyInAnyOrder(
                tuple(1, "Cereais e derivados"),
                tuple(2, "Verduras, hortaliças e derivados"),
                tuple(3, "Frutas e derivados")
            );
    }

    @Test
    public void buscarTodos_deveListarTodosOsAlimentosComPaginacao_quandoSolicitadoSemFiltros() {
        assertThat(alimentoService.buscarTodos(getPage(), new AlimentoFiltros()))
            .hasSize(9)
            .extracting("id", "descricao", "categoria")
            .containsExactlyInAnyOrder(
                tuple(1, "Arroz, integral, cozido", "Cereais e derivados"),
                tuple(16, "Bolo, pronto, chocolate", "Cereais e derivados"),
                tuple(24, "Cereais, mistura para vitami, trigo, cevada e aveia", "Cereais e derivados"),
                tuple(27, "Creme de arroz, pó", "Cereais e derivados"),
                tuple(32, "Farinha, de centeio, integral", "Cereais e derivados"),
                tuple(55, "Pastel, de carne, cru", "Cereais e derivados"),
                tuple(116, "Couve, manteiga, refogada", "Verduras, hortaliças e derivados"),
                tuple(135, "Mostarda, folha, crua", "Verduras, hortaliças e derivados"),
                tuple(165, "Abacaxi, polpa, congelada", "Frutas e derivados")
            );
    }

    @Test
    public void buscarTodos_deveListarTodosOsAlimentosComPaginacao_quandoSolicitadoComFiltrosDeCategoria() {
        var filtros = new AlimentoFiltros();
        filtros.setCategoria("verd");
        assertThat(alimentoService.buscarTodos(getPage(), filtros))
            .hasSize(2)
            .extracting("id", "descricao", "categoria")
            .containsExactlyInAnyOrder(
                tuple(116, "Couve, manteiga, refogada", "Verduras, hortaliças e derivados"),
                tuple(135, "Mostarda, folha, crua", "Verduras, hortaliças e derivados")
            );
    }

    @Test
    public void buscarTodosSemPaginacao_deveListarTodosOsAlimentosSemPaginacao_quandoSolicitadoSemFiltros() {
        assertThat(alimentoService.buscarTodosSemPaginacao(new AlimentoFiltros()))
            .hasSize(9)
            .extracting("id", "descricao", "categoria")
            .containsExactlyInAnyOrder(
                tuple(1, "Arroz, integral, cozido", "Cereais e derivados"),
                tuple(16, "Bolo, pronto, chocolate", "Cereais e derivados"),
                tuple(24, "Cereais, mistura para vitami, trigo, cevada e aveia", "Cereais e derivados"),
                tuple(27, "Creme de arroz, pó", "Cereais e derivados"),
                tuple(32, "Farinha, de centeio, integral", "Cereais e derivados"),
                tuple(55, "Pastel, de carne, cru", "Cereais e derivados"),
                tuple(116, "Couve, manteiga, refogada", "Verduras, hortaliças e derivados"),
                tuple(135, "Mostarda, folha, crua", "Verduras, hortaliças e derivados"),
                tuple(165, "Abacaxi, polpa, congelada", "Frutas e derivados")
            );
    }

    @Test
    public void buscarTodosSemPaginacao_deveListarTodosOsAlimentosSemPaginacao_quandoSolicitadoComFiltrosDeCategoria() {
        var filtros = new AlimentoFiltros();
        filtros.setCategoria("verd");

        assertThat(alimentoService.buscarTodosSemPaginacao(filtros))
            .hasSize(2)
            .extracting("id", "descricao", "categoria")
            .containsExactlyInAnyOrder(
                tuple(116, "Couve, manteiga, refogada", "Verduras, hortaliças e derivados"),
                tuple(135, "Mostarda, folha, crua", "Verduras, hortaliças e derivados")
            );
    }

    @Test
    public void buscarPorId_deveRetornarAlimento_quandoInformadoId() {
        var alimento = alimentoService.buscarPorId(16);

        assertThat(alimento).isNotNull();
        assertThat(alimento.getId()).isEqualTo(16);
        assertThat(alimento.getDescricao()).isEqualTo("Bolo, pronto, chocolate");
        assertThat(alimento.getCategoria()).isEqualTo("Cereais e derivados");
    }

    @Test
    public void buscarPorId_deveLancarException_quandoAlimentoNaoForEncontrado() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> alimentoService.buscarPorId(16516))
            .withMessage("O alimento não foi encontrado.");
    }
}
