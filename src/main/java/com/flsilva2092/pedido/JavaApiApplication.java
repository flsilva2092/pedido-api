package com.flsilva2092.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


/**
 * Class that starts the application
 * 
 * @author Francisco Silva
 * @since 17/03/2021
 */
//@Log4j2

@SpringBootApplication
public class JavaApiApplication {
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(JavaApiApplication.class);

		SpringApplication.run(JavaApiApplication.class, args);
		logger.info("JavaAPI iniciado com sucesso em " + LocalDateTime.now());
	}

}
