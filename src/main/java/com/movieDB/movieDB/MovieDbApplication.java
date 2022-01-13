package com.movieDB.movieDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan ({"com.movieDB.movieDB", "com.movieDB.movieDB.config", "com.movieDB.movieDB.controller",
//		"com.movieDB.movieDB.model", "com.movieDB.movieDB.services"})
//@EnableJpaRepositories ("com.movieDB.movieDB.repositories")
public class MovieDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDbApplication.class, args);
	}

}
