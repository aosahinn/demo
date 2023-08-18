package com.preschool.demo;

import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setFirstName("oguz");
		user.setLastName("sahin");
		user.setUsername("aos316");
		user.setPassword("password");

		userService.save(user);
		Optional<User> find = userService.findByUsername("aos316");

		System.out.println("aos316 aranıyor: {}" + find);
		//System.out.println("aos316 aranıyor: " + user);

	}
}
