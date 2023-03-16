package br.com.nutriclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.nutriclinic.service.TokenService;
import br.com.nutriclinic.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Autowired
	private TokenService tokenService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
			UserDetailServiceImpl userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder)
				.and()
				.build();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> {
			try {
				authorize.requestMatchers(HttpMethod.POST, "/auth").permitAll()
					.anyRequest().authenticated()
					.and()
					.cors().and().csrf().disable()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
					.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService),
							UsernamePasswordAuthenticationFilter.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return http.build();
	}
	
	public static void main(String[] args) {
		String senha = new BCryptPasswordEncoder().encode("123456");
		System.out.println(senha);
	}

}
