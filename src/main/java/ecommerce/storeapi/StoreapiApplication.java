package ecommerce.storeapi;

import ecommerce.storeapi.connection.RegisterRequest;
import ecommerce.storeapi.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static ecommerce.storeapi.util.Role.ADMIN;
import static ecommerce.storeapi.util.Role.MANAGER;
import static ecommerce.storeapi.util.Role.GUEST;

@SpringBootApplication
public class StoreapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreapiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

			var guest = RegisterRequest.builder()
					.firstname("Guest Name")
					.lastname("Guest LastName")
					.email("guest@mail.com")
					.password("password")
					.role(GUEST)
					.build();
			System.out.println("Guest token: " + service.register(guest).getAccessToken());

		};
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:5174");
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:5173");
			}
		};
	}
}
