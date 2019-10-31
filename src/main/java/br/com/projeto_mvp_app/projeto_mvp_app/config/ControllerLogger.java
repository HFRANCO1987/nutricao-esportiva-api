//package br.com.projeto_mvp_app.projeto_mvp_app.config;
//
//import br.com.projeto_mvp_app.projeto_mvp_app.modules.log.service.LogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.InitBinder;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component
//@ControllerAdvice
//public class ControllerLogger {
//
//    @Autowired
//    private LogService logService;
//
//    @InitBinder
//    public void initBinder(HttpServletRequest request) {
//        logService.gerarLogUsuario(request);
//    }
//}