package com.bembuscado.bembuscado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembuscado.bembuscado.entity.Contato;

@Repository
public interface ContatoDao extends JpaRepository<Contato, Long> {

}
