package be.opcodes.harmoni.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "be.opcodes.harmoni")
public class HarmoniApplication {
    public static void main(String[] args) {
        SpringApplication.run(HarmoniApplication.class, args);
    }
}
