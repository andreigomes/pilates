package com.pilates;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pilates.model.Categoria;
import com.pilates.repositories.CategoriaRepository;

@SpringBootApplication
public class PilatesApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PilatesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	Categoria categoria1 = new Categoria(null, "Informática");
	Categoria categoria2 = new Categoria(null, "Escritório");
	
	categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		
	}

}
