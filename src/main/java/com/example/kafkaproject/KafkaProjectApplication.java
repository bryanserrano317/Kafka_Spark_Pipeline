package com.example.kafkaproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.Random;


@SpringBootApplication
public class KafkaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProjectApplication.class, args);
    }

    Random random = new Random();
    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        long startTime = System.currentTimeMillis();

        // Your program code goes here
        return args -> {
            for(int i=0; i < 1000000; i++){
                kafkaTemplate.send("playerLocationData", "playerCoordinate: " + Math.round(random.nextGaussian() * 7 + 35) + "," + Math.round(random.nextGaussian() * 5 + 17) +
                                                                                          " " + i + " ms");
            }
            long endTime = System.currentTimeMillis();
            long runtime = endTime - startTime;
            System.out.println("Runtime: " + runtime + "ms");
        };
    }
}
