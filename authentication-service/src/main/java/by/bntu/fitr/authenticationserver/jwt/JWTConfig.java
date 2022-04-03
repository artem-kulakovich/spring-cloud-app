package by.bntu.fitr.authenticationserver.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jwt.properties")
public class JWTConfig {
    @Bean
    public JWTProvider getJwtProvider() {
        return new JWTProviderImpl();
    }

    @Bean
    public JWTValidator getJwtValidator() {
        return new JWTValidatorImpl();
    }
}
