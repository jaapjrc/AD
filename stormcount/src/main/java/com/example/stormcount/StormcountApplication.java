package com.example.stormcount;


import com.example.stormcount.entity.Article;
import com.example.stormcount.entity.Card;
import com.example.stormcount.service.ArticleService;
import com.example.stormcount.service.CardService;
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

	@Autowired
	CardService cardService;

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
			if(articleService.findAll().size()<29) {
				for (int i = 0; i < 30; i++) {
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

	@Bean
	CommandLineRunner putCards(){
		return args -> {

			Faker faker = new Faker(new Locale("es-ES"));
			if(cardService.findAll().size()<29) {
				for (int i = 0; i < 30; i++) {
					Card c = new Card();
					c.setCardname("ExampleCard" + i);
					c.setCardTypes("Creature");
					c.setEffect(faker.lorem().characters(100));

					cardService.save(c);
				}
			}
		};
	}

}
