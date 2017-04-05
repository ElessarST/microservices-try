package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigServiceApplication {

	static void main(String[] args) {
		SpringApplication.run ConfigServiceApplication, args
	}
}
