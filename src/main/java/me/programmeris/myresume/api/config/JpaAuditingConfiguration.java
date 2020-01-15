package me.programmeris.myresume.api.config;

import me.programmeris.myresume.api.session.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.of(Session.user.get() != null ? Session.user.get().getId() : 0L);
    }
}