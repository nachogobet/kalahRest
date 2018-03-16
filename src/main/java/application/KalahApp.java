package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gobet on 16-3-18.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("controllers")
public class KalahApp {
    public static void main(String[] args){
        SpringApplication.run(KalahApp.class, args);
    }
}
