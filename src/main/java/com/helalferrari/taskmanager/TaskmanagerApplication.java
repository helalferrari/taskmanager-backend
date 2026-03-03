package com.helalferrari.taskmanager;

import com.helalferrari.taskmanager.model.Task;
import com.helalferrari.taskmanager.model.User;
import com.helalferrari.taskmanager.repository.TaskRepository;
import com.helalferrari.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// 1. Criar Usuário de Teste
			if (userRepository.findByEmail("admin@example.com").isEmpty()) {
				User admin = User.builder()
						.email("admin@example.com")
						.password(passwordEncoder.encode("123"))
						.build();
				userRepository.save(admin);
				System.out.println("Usuário de teste 'admin@example.com' criado.");

				// 2. Criar Tarefas para este usuário
				Task t1 = Task.builder()
						.user(admin)
						.title("Configurar ambiente Spring Boot")
						.completed(true)
						.deleted(false)
						.build();
				
				Task t2 = Task.builder()
						.user(admin)
						.title("Implementar JWT Authentication")
						.completed(true)
						.deleted(false)
						.build();
				
				Task t3 = Task.builder()
						.user(admin)
						.title("Testar endpoints no Swagger")
						.completed(false)
						.deleted(false)
						.build();

				taskRepository.saveAll(Arrays.asList(t1, t2, t3));
				System.out.println("Tarefas de teste criadas para o admin.");
			}
		};
	}
}
