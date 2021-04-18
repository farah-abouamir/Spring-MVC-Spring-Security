package com.enset.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration // traiter au démarage de l'app
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    //comment chercher les usr?
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        System.out.println("----------------------------");
        System.out.println(passwordEncoder.encode("1234"));
        System.out.println("----------------------------");
        //------------------------------------------------------------------jdbc Authentication
        //requete  executé pour chercher utilisateur et mdp a partir bdd
        //requete executé pour chercher les roles
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role from users_roles where username=?")
                .passwordEncoder(passwordEncoder).rolePrefix("ROLE_");
       //----------------------------------------------------------------------UserDetailService



         //---------------------------------------------------------------Memory Authentication
        //super.configure(auth); // par defaut utilise un seul usr
       /* auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER"); // stocker users en memoire
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN");
     */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login"); // utiliser form d'auten
      //  http.formLogin();
        //http.httpBasic();
        //http.authorizeRequests().anyRequest().authenticated(); // tous les requetes nécessite une authntific
        http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");
        //http.authorizeRequests().antMatchers("/user/**").permitAll();
        http.authorizeRequests().antMatchers("/login","/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated(); // ts les requets http ncessite de passer par authn
        http.csrf();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }
    @Bean    // objet retourner par cette méthode--> sera dans contexte de l'app
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
