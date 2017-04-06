package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard

@SpringBootApplication
@EnableHystrixDashboard
class MonitoringApplication {

	static void main(String[] args) {
		SpringApplication.run MonitoringApplication, args
	}
}
