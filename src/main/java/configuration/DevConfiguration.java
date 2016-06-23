package configuration;

import bootstrap.DatabaseBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfiguration {

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }


}
