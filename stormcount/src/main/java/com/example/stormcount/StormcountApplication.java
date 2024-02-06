package com.example.stormcount;


import com.example.stormcount.entity.Article;
import com.example.stormcount.service.ArticleService;
import com.example.stormcount.storage.StorageProperties;
import com.example.stormcount.storage.StorageService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class StormcountApplication {

	@Autowired
	ArticleService articleService;

	public static void main(String[] args) {
		SpringApplication.run(StormcountApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	CommandLineRunner putArticles(){
		return args -> {

			Faker faker = new Faker(new Locale("es-ES"));
			if(articleService.findAll().size()<31) {
				for (int i = 0; i < 25; i++) {
					Article a = new Article();
					a.setTitle(faker.zelda().game());
					a.setText(faker.lorem().characters(100));
					a.setAuthor(faker.leagueOfLegends().champion());
					a.setPublishing(LocalDate.now());

					articleService.save(a);
					}
				}
		};
	}

}
