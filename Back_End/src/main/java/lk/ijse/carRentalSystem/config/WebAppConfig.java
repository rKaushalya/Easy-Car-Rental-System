package lk.ijse.carRentalSystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lk.ijse.carRentalSystem.controller","lk.ijse.carRentalSystem.advisor"})
public class WebAppConfig {
    public WebAppConfig(){
        System.out.println("WebApp Config class Initialized.!");
    }
}
