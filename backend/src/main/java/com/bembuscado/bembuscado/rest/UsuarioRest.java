package com.bembuscado.bembuscado.rest;

import java.security.NoSuchAlgorithmException;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bembuscado.bembuscado.dao.UsuarioDao;
import com.bembuscado.bembuscado.entity.Usuario;
import com.bembuscado.bembuscado.enums.PerfilEnum;
import com.bembuscado.bembuscado.response.Response;
import com.bembuscado.bembuscado.utils.PasswordUtils;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRest {

	@Autowired
	private UsuarioDao usuarioDao;

	@GetMapping
	public Optional<Optional<Usuario>> findByEmail(@RequestParam("email") String email) {
		return Optional.ofNullable(usuarioDao.findByEmail(email));
	}

	@PostMapping
	public ResponseEntity<Response<Usuario>> cadastrar(@Valid @RequestBody Usuario usuario, BindingResult result)
			throws NoSuchAlgorithmException {
		Response<Usuario> response = new Response<Usuario>();
		
		validarUsuarioExistente(usuario, result);
		Usuario usuario2 = this.senhaPerfil(usuario, result);
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		usuarioDao.save(usuario2);
		return ResponseEntity.ok(response);
	}

	//void post(@RequestBody Usuario usuario) {
		//usuarioDao.save(usuario);
	//}

	//@DeleteMapping("/{id}")
	//public void delete(@PathVariable("id") Integer id) {
		//usuarioDao.deleteById(id);
	//}

	private void validarUsuarioExistente(Usuario usuario, BindingResult result) {
		this.usuarioDao.findByEmail(usuario.getEmail())
				.ifPresent(usu -> result.addError(new ObjectError("usuario", "Usuário já Existente")));
	}
	
	private Usuario senhaPerfil(Usuario usuario, BindingResult result) throws NoSuchAlgorithmException {
		
		Usuario usuario2 = new Usuario();
		usuario2.setEmail(usuario.getEmail());
		usuario2.setNome(usuario.getNome());
		usuario2.setSenha(PasswordUtils.gerarBCrypt(usuario.getSenha()));
		usuario2.setPerfi(PerfilEnum.ROLE_USUARIO);
		
		return usuario2;
		
	}
	
}
