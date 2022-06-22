package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/board","/account/**","/css/**","/images/**","/loginLogo/**","/board/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        log.info("ok?={}","yes");
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled "
                        + "from member "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select m.username, r.name "
                        + "from member_role mr inner join member m on mr.member_id = m.id "
                        + "inner join role r on mr.role_id = r.id "
                        + "where m.username = ?")
                .dataSource(dataSource);
    }
//    @ConfigurationProperties("spring.datasource")
//    @Bean
//    public static DataSource dataSource(){
//        return DataSourceBuilder.create().type(OracleDataSource.class).build();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
