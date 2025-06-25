package dev.fousin.movieflix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI getOpenAPI(){
        Info info = new Info();
        Contact contact = new Contact();
        contact.setUrl("https://github.com/fousin");
        contact.name("Fousin");
        contact.email("andersoncarlos01@hotmail.com");

        info.title("MovieFlix API");
        info.version("V1.0.0");
        info.description("Aplicação para gerenciamento de catalogo de filmes, com autenticação de usuários.");
        info.contact(contact);

        return new OpenAPI().info(info);
    }

}
