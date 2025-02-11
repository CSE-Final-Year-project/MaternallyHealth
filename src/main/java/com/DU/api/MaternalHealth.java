package com.DU.api;

import com.DU.api.service.AuthFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(info = @Info(title = "The Power of 1000 days Maternal health", version = "1.0", description = "this is the Maternal health system to support our mothers"
        + "\n"
        + " We offer premium health facilities . we are proud to see our Mothers Happy !!", license = @License(name = "Tell:+250788483455 | +250726203911", url = "  "), contact = @Contact(url = " ", name = " Maternal Health", email = "janvier.rutagengwa@gmail.com")))

@SpringBootApplication

@EnableJpaAuditing
public class MaternalHealth {
    
    public static void main(String[] args) {
        SpringApplication.run(MaternalHealth.class, args);

    }

 @EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
    }

}

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowedOrigins("*");
    }
}


    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns(
                "/api/v1/staffs/*",
                "/api/v1/agents/*",
                // "/api/v1/agents/location/*",
                "/api/v1/clients/*",
               // "/api/v1/branches/",
                "/api/v1/logs/*",
                "/api/v1/User/setrole/*",
                "/api/v1/Baby/*",
                "/api/v1/User/logout");
        return registrationBean;
    }
}
