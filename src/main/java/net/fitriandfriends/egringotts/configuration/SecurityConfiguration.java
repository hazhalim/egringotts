package net.fitriandfriends.egringotts.configuration;

import net.fitriandfriends.egringotts.AuthenticationSuccessHandler;
import net.fitriandfriends.egringotts.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//
//        // Data access object
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//
//        return provider;
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        http
//            .csrf(AbstractHttpConfigurer::disable) // Disable the CSRF protection
//
//            .authorizeHttpRequests(authorize -> authorize
//                    .requestMatchers("/", "/signin", "/signup").permitAll() // Allow access to these paths without authentication
//
//                    .requestMatchers("/dashboard").hasAnyAuthority("Platinum Patronus", "Golden Galleon", "Silver Snitch")
//                    .requestMatchers("/admin").hasAuthority("Goblin")
//                    .anyRequest()
//                    .authenticated()
//            ).httpBasic(Customizer.withDefaults());

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {

                    registry.requestMatchers("/", "/signin/**", "/signup/**", "/forgot/**", "/emails/test", "/securityquestions/all").permitAll();
                    registry.requestMatchers(
                            "/dashboard/**",
                            "/transactionshistory/**",
                            "/currencyexchange/**",
                            "/expenditureanalysis/**",
                            "/settings/**",
                            "/transfer/**",
                            "/balance/**",
                            "/Addcard/**").hasAnyRole("Platinum Patronus", "Golden Galleon", "Silver Snitch");
                    registry.requestMatchers(
                            "/admin",
                            "/currencies/**"
                            ).hasRole("Goblin");
                    registry.anyRequest().authenticated();

                })
                // After registry.requestMatchers (latest one)
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("/signin")
                            .successHandler(authenticationSuccessHandler)
                            .permitAll();

                })


                .build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails normalUser = User.builder()
//                .username("user")
//                .password("$2a$12$VLMh3duXBl8eYtz1.FO/ueKLuYyUZuBlM9SoVAJvYIqRVYeHOlrpi")
//                .roles("Platinum Patronus")
//                .build();
//
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password("$2a$12$qeoFm9pODvFn88UVxL4CP.9oz2G0ypBRUFRkSz4J3zSoopOuqsAqC")
//                .roles("Goblin", "Platinum Patronus")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//
//    }

    @Bean
    public UserDetailsService userDetailsService() {

        return customUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        // Data access object (loading users from database for authentication)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

}

// .formLogin(formLogin -> formLogin
//                    .loginPage("/signin")
//                    .defaultSuccessUrl("/menu", true))
//            .logout(logout -> logout
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/login?logout"));

//        return http.
//                authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                .oauth2Login(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .build();

// After registry.requestMatchers (latest one)
//.formLogin(httpSecurityFormLoginConfigurer -> {
//        httpSecurityFormLoginConfigurer.loginPage("/signin").permitAll()
//                            .defaultSuccessUrl("/dashboard", true);
//                })