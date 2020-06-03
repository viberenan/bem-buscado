package com.bembuscado.bembuscado.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembuscado.bembuscado.entity.Usuario;


@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByEmail(String email);
}
