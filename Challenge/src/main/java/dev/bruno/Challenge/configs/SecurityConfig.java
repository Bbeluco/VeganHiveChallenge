package dev.bruno.Challenge.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtApplicationFilter jwtApplicationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/users/**").permitAll()
        ).authorizeHttpRequests(
                authorize -> authorize.anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
        .addFilterBefore(jwtApplicationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
