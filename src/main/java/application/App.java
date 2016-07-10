package application;

import bootstrap.DatabaseBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

public class App {

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
