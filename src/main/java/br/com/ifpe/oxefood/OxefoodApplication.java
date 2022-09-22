package br.com.ifpe.oxefood;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ifpe.oxefood.servicos.acesso.Usuario;

/**
 * @author Roberto Alencar
 *
 */
@SpringBootApplication
public class OxefoodApplication {

    public static void main(String[] args) {
	SpringApplication.run(OxefoodApplication.class, args);
    }

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

    @Bean
    public AuditorAware<Usuario> auditor() {
	return () -> Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
		.filter(Authentication::isAuthenticated).map(Authentication::getPrincipal).map(Usuario.class::cast);
    }

}