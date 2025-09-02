package com.blogJPA.localtest.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SystAuthUsers {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService (PasswordEncoder encoder){

        UserDetails guest = User
                .builder()
                .username("invitado")
                .password(encoder.encode("1234"))
                .roles("INVITADO")
                .build();

        UserDetails admin = User
                .builder()
                .username("admin")
                //.passwordEncoder(encoder::encode).password("admin123")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(guest, admin);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Necesario para REST
                .authorizeHttpRequests(auth -> auth
                        //Permit All interfiere en la API REST
                        .requestMatchers("/api/content", "/api/content/all").hasRole("INVITADO")
                        .requestMatchers("/api/content", "/api/content/all").hasRole("ADMIN")
                        .requestMatchers("/api/content/insertOne").hasRole("ADMIN")
                        .requestMatchers("/api/content/insertTwo").hasRole("ADMIN")
                        .requestMatchers("/api/content/update/**").hasRole("ADMIN")

                )
                //Interfiere con la API REST .formLogin(form -> form.permitAll()).logout(logout -> logout.permitAll());
        .httpBasic(Customizer.withDefaults()) // ← clave para REST
                .logout(logout -> logout.permitAll());

        return http.build();

    }







}
