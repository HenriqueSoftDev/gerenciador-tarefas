package br.senac.tads.dsw.gerenciador.tarefas;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Cors {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // libera todas as rotas
                        .allowedOrigins("http://localhost:5500") // endereço onde seu frontend roda
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos permitidos
                        .allowCredentials(true);
            }
        };
    }
}
