package com.zcc.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.zcc.login.common.handler.LoginSuccessHandler;
import com.zcc.login.common.interceptor.AuthenticationTokenFilter;
import com.zcc.login.user.CustomUserService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService userService;
	@Autowired
	private LoginSuccessHandler successHandler;
	@Autowired
	private AuthenticationTokenFilter filter;

	// TODO: 2017/10/30  http://blog.csdn.net/eunyeon/article/details/52892028
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			// don't create session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers(
				HttpMethod.GET,
				"/",
				"/*.html",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.png",
				"/**/*.ttf",
				"/**/*.css",
				"/**/*.js",
				"/**/*.gif"
			).permitAll()
			.antMatchers(
				"/login/**",
				"/swagger*/**",
				"/v2/api-docs",
				"/demo",
				"/",
				"/login",
				"/logout"
			).permitAll()
			.anyRequest().authenticated();
		// Custom JWT based security filter
		// disable page caching
		http.headers().cacheControl();
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
//			.passwordEncoder(passwordEncoder());
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
