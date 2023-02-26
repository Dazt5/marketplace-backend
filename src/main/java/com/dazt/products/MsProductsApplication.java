package com.dazt.products;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@NoArgsConstructor
@SpringBootApplication
@ComponentScan(basePackages = {"com.dazt"})
@EntityScan(basePackages = {"com.dazt"})
@EnableJpaRepositories(basePackages = {"com.dazt"})
public class MsProductsApplication {

	/** timezoneDefault. */
	@Value("${timezone.default}")
	private String timezoneDefault;

	/**
	 * TimeZone por Defecto.
	 */
	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(this.timezoneDefault));
	}

	public static void main(String[] args) {
		SpringApplication.run(MsProductsApplication.class, args);
	}

}
