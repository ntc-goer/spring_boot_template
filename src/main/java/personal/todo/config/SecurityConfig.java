package personal.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.function.Function;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String USER_NAME = "user";
    private final String PASSWORD = "asas";

    @Bean
    public UserDetailsService userDetailsService() {
        Function<String, String> encoder = input -> passwordEncoder().encode(input);
        UserDetails user1 = User.builder()
                .passwordEncoder(encoder)
                .username(USER_NAME)
                .password(PASSWORD)
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .passwordEncoder(encoder)
                .username("test")
                .password("test")
                .roles("USER")
                .build();

        UserDetails user3 = User.builder()
                .passwordEncoder(encoder)
                .username("sa")
                .password("sa")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
}
