package tw.org.cmaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("tw.org.cmaas")
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class CMAAS2027Application {
	public static void main(String[] args) {
		SpringApplication.run(CMAAS2027Application.class, args);
	}
}
