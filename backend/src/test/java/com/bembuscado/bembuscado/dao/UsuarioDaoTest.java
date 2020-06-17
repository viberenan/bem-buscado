package com.bembuscado.bembuscado.dao;


import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bembuscado.bembuscado.entity.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioDaoTest {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	private static final String email = "teste@teste.com";
	
	@Before
	public void setUp() throws Exception{
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@teste.com");
		usuario.setNome("teste");
		usuario.setSenha("123456");
		this.usuarioDao.save(usuario);
	}
	
	@After
	public final void tearDown() {
		this.usuarioDao.deleteAll();
	}
	
	@Test
	public void findByEmail() {
		Optional<Usuario> usuario = (this.usuarioDao.findByEmail(email));	
		assertEquals(email, usuario.get().getEmail());
	}
	

}
