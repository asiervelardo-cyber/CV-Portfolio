package com.example.CV_Portfolio.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Aquí se configura el cambio de idioma de toda la web (español e inglés).
// Es la misma clase que el proyecto P6 del profesor.
@Configuration
public class ConfiguracionIdioma implements WebMvcConfigurer {

    // Guarda el idioma elegido en la sesión, así se mantiene al cambiar de página.
    // Si todavía no se ha elegido ninguno, la web sale en español.
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolvedor = new SessionLocaleResolver();
        resolvedor.setDefaultLocale(Locale.forLanguageTag("es"));
        return resolvedor;
    }

    // Permite cambiar el idioma añadiendo ?lang=en o ?lang=es a cualquier ruta.
    // También funciona con los botones del editor, que lo envían por POST.
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    // Activa lo anterior: Spring mira el parámetro "lang" antes de llegar al controlador.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
