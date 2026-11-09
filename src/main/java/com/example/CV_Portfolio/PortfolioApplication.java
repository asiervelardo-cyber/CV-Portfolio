package com.example.CV_Portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta es la clase que arranca toda la aplicación.
// Está fuera de las carpetas (config, controller, model, form) para que Spring
// encuentre todas las clases que hay dentro de ellas.
@SpringBootApplication
public class PortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }

}
