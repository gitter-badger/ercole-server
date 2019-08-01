package io.ercole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Application boot.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    /**
     * @param args = Spring arguments, eventually, for 1st level properties.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
