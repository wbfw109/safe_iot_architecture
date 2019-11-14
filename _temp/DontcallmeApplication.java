package com.practice.dontcallme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DontcallmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DontcallmeApplication.class);
	}
}


/*
	gradle bootRun 이 아닌, main() 메소드를 갖는 ~Application.java 를 실행시켜줘야함.
*/