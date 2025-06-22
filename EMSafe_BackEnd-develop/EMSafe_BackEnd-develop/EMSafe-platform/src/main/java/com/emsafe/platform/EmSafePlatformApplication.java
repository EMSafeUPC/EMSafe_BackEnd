package com.emsafe.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
    "com.emsafe.platform.em.domain.model.aggregates.map",
    "com.emsafe.platform.em.domain.model.aggregates.devices",
    "com.emsafe.platform.auth.user"
})
@EnableJpaRepositories(basePackages = {
    "com.emsafe.platform.em.infrastructure.persistance.jpa.map",
    "com.emsafe.platform.em.infrastructure.persistance.jpa.devices",
    "com.emsafe.platform.auth"
})
@ComponentScan(basePackages = "com.emsafe.platform")
public class EmSafePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmSafePlatformApplication.class, args);
    }
} 