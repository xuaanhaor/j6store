package poly.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class AsmApplication {
	public static void main(String[] args) {
		SpringApplication.run(AsmApplication.class, args);
	}
	@Value("${spring.web.resources.static-locations}")
	private String[] locations;
	@Bean
	CommandLineRunner run(){
		return args -> {
			log.info(">>: {}", locations);
		};
	}

}
