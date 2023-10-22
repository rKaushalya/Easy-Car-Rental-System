package lk.ijse.carRentalSystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JPAConfig.class})
@ComponentScan(basePackages = "lk.ijse.carRentalSystem.service")
public class WebRootConfig {
    public WebRootConfig(){
        System.out.println("WebRoot Config initialized.!");
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
