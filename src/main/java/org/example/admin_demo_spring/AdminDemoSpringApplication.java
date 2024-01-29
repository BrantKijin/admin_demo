package org.example.admin_demo_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@EnableJpaAuditing
@SpringBootApplication
// @EnableScheduling
// @EnableSchedulerLock(defaultLockAtMostFor = "PT60S")
public class AdminDemoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminDemoSpringApplication.class, args);
	}

}
