package com.bembuscado.bembuscado.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bembuscado.bembuscado.dao.UsuarioDao;
import com.bembuscado.bembuscado.entity.Usuario;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usu = usuarioDao.findByEmail(username);

		if (usu.isPresent()) {
			return JwtUserFactory.create(usu.get());
		}

		throw new UsernameNotFoundException("Email não encontrado");
	}

}
