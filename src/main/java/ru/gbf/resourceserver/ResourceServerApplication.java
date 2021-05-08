package ru.gbf.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
value = {"com.gbf.auth.filter.integration","ru.gbf.resourceserver.service","ru.gbf.resourceserver.controller",
        "ru.gbf.resourceserver.config","ru.gbf.resourceserver.security","ru.gbf.resourceserver.dao",
        "ru.gbf.resourceserver.mapper"})
@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}
