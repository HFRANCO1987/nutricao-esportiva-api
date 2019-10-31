package br.com.projeto_mvp_app.projeto_mvp_app;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
//@ComponentScan(basePackageClasses = {ProjetoMvpAppApplication.class})
public class ProjetoMvpAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoMvpAppApplication.class, args);
    }
}