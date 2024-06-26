
package com.example.guchiBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 開発用コード、コード内でユーザ定義

	/*
	 * @Bean InMemoryUserDetailsManager userDetailsService() { UserDetails admin =
	 * User.withUsername("admin").password(passwordEncoder().encode("admin1234")).
	 * roles("ADMIN") .build(); UserDetails student =
	 * User.withUsername("student").password(passwordEncoder().encode("student5678")
	 * ) .roles("USER").build(); return new InMemoryUserDetailsManager(admin,
	 * student); }
	 */

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.ignoringRequestMatchers("/downloadFile", "/polly"))// ダウンロードは認証なし
				// フォーム認証を使う
				.formLogin(login -> login.permitAll()) // フォーム認証画面は認証不要
				.authorizeHttpRequests(authz -> authz.requestMatchers("/css/**").permitAll() // CSSファイルは認証不要
						.requestMatchers("/main", "/signup", "/user", "user/signup", "/user/signup", "signup").permitAll() // トップページ,サインアップは認証不要
						.anyRequest().authenticated()// 他のURLはログイン後アクセス可能
				);
		/* ログアウト */
		http.logout(logout -> {
			logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		});
		/* ログイン後、ホームに遷移 */
		 http.formLogin(form->{
	            form.defaultSuccessUrl("/main");
	        });

		return http.build();
	}
}
