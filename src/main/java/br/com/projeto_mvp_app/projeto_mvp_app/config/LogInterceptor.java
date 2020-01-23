package br.com.projeto_mvp_app.projeto_mvp_app.config;

import br.com.projeto_mvp_app.projeto_mvp_app.modules.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogService logService;
    @Value("${app-config.enable-rabbit}")
    private Boolean rabbitEnabled;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (rabbitEnabled) {
            logService.gerarLogUsuario(request);
        }
        return true;
    }
}
