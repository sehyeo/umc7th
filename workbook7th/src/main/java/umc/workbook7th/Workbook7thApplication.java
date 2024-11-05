package umc.workbook7th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Workbook7thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Workbook7thApplication.class, args);
	}

}
