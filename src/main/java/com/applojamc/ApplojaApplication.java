package com.applojamc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.applojamc.domain.Categoria;
import com.applojamc.repositories.CategoriaRepository;

@SpringBootApplication
public class ApplojaApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(ApplojaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório"); 
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); 
	}
}
