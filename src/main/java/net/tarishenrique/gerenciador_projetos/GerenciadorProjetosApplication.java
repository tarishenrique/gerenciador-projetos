package net.tarishenrique.gerenciador_projetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GerenciadorProjetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorProjetosApplication.class, args);
	}

}
