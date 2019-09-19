package com.dito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.dito.repository")
@EntityScan("com.dito.entities")
public class Startup 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Startup.class, args);
    }
}
