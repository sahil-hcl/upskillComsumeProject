package com.hcl.upSkillConsumeProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableCaching
@SpringBootApplication
@EnableFeignClients
public class UpSkillConsumeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpSkillConsumeProjectApplication.class, args);
	}

}
