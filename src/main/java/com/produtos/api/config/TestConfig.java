package com.produtos.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.produtos.api.services.DBService;

@Configuration
@Profile("test") // sempre que o perfil de teste esteja ativo irá chamar o metodo bean, chamando o instanciadb 
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean 
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}

}
