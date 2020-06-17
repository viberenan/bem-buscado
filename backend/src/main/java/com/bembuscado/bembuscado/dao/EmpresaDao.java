package com.bembuscado.bembuscado.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembuscado.bembuscado.entity.Empresa;
import com.bembuscado.bembuscado.entity.Usuario;

@Repository
public interface EmpresaDao extends JpaRepository<Empresa, Long> {
	
	Optional<Empresa> findByCnpj(String cnpj);
	Optional<Empresa> findByUsuarioId(long usuarioId);
	Empresa findById(Long id);
}
