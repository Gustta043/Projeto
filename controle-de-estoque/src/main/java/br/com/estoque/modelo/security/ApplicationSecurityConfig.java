package br.com.estoque.modelo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.estoque.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordConfig passwordEncoder;
	private final UsuarioService usuarioService;

	public ApplicationSecurityConfig(PasswordConfig passwordEncoder, UsuarioService usuarioService) {
		this.passwordEncoder = passwordEncoder;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "index", "/register")
			.permitAll()
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/estoque")
		.and()
		.logout()
			.logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true)
			.deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder.passwordEncoder());
		provider.setUserDetailsService(usuarioService);
		return provider;
	}

}
