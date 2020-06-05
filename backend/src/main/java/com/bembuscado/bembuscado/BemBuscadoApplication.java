package com.bembuscado.bembuscado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bembuscado.bembuscado.entity.Usuario;
import com.bembuscado.bembuscado.enums.PerfilEnum;

@SpringBootApplication
public class BemBuscadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BemBuscadoApplication.class, args);
	}
}
