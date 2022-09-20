package com.preschool.demo;

import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private UserService userService;

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
		//Optional<User> find = userService.findByUsername("aos316");

		//System.out.println("aos316 aranıyor: {}" + find);
		System.out.println("aos316 aranıyor: " + user);

	}
}
