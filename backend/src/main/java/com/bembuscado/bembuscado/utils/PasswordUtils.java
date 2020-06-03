package com.bembuscado.bembuscado.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	public PasswordUtils() {
		
	}
	
	public static String gerarBCrypt(String senha) {
		if (senha == null) {
			return senha;
		}
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(senha);
	}
	


}
