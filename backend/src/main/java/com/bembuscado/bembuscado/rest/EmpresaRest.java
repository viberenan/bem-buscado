package com.bembuscado.bembuscado.rest;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bembuscado.bembuscado.dao.ContatoDao;
import com.bembuscado.bembuscado.dao.EmpresaDao;
import com.bembuscado.bembuscado.dao.UsuarioDao;
import com.bembuscado.bembuscado.dto.CadastrarEmpresaContato;
import com.bembuscado.bembuscado.entity.Contato;
import com.bembuscado.bembuscado.entity.Empresa;
import com.bembuscado.bembuscado.entity.Usuario;
import com.bembuscado.bembuscado.enums.PerfilEnum;
import com.bembuscado.bembuscado.response.Response;
import com.bembuscado.bembuscado.utils.PasswordUtils;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class EmpresaRest {

	@Autowired
	private EmpresaDao empresaDao;

	@Autowired
	private ContatoDao contatoDao;

	@Autowired
	private UsuarioDao usuarioDao;

	//Cadastrar Empresa
	@PostMapping
	public ResponseEntity<Response<CadastrarEmpresaContato>> cadastrar(@Valid @RequestBody 
			CadastrarEmpresaContato cadastrarEmpresaContato,@AuthenticationPrincipal Usuario logado,
			BindingResult result) throws NoSuchAlgorithmException {
		
		Response<CadastrarEmpresaContato> response = new Response<CadastrarEmpresaContato>();
		validarDadosExistente(cadastrarEmpresaContato, result);
		
		Empresa empresa = this.converterDtoEmpresa(cadastrarEmpresaContato);
		Contato contato = this.converterDtoContato(cadastrarEmpresaContato, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		empresa.setUsuario(logado);
		this.empresaDao.save(empresa);
		contato.setEmpresa(empresa);
		this.contatoDao.save(contato);

		response.setData(this.converterCadastroEmpresaContato(contato));
		return ResponseEntity.ok(response);
	}
	
	private void validarDadosExistente(CadastrarEmpresaContato cadastrarEmpresaContato, BindingResult result) {
		this.empresaDao.findByCnpj(cadastrarEmpresaContato.getCnpj())
				.ifPresent(usu -> result.addError(new ObjectError("empresa", "Empresa já Existente")));
	}
	
	// Converter Dto Empresa
	private Empresa converterDtoEmpresa(CadastrarEmpresaContato cadastrarEmpresaContato) {
		Empresa empresa = new Empresa();
		empresa.setBairro(cadastrarEmpresaContato.getBairro());
		empresa.setCep(cadastrarEmpresaContato.getCep());
		empresa.setCidade(cadastrarEmpresaContato.getCidade());
		empresa.setCnpj(cadastrarEmpresaContato.getCnpj());
		empresa.setNumero(cadastrarEmpresaContato.getNumero());
		empresa.setRua(cadastrarEmpresaContato.getRua());
		empresa.setUf(cadastrarEmpresaContato.getUf());
		empresa.setRazaoSocial(cadastrarEmpresaContato.getRazaoSocial());
		return empresa;
	}

	// Converter Dto Contato
	private Contato converterDtoContato(CadastrarEmpresaContato cadastrarEmpresaContato, BindingResult result) {
		Contato contato = new Contato();
		contato.setContato(cadastrarEmpresaContato.getContato());
		contato.setTipoContato(cadastrarEmpresaContato.getTipocontato());
		return contato;
	}

	// Popular Dto de cadastro
	private CadastrarEmpresaContato converterCadastroEmpresaContato(Contato contato) {
		CadastrarEmpresaContato cadastrarEmpresaContato = new CadastrarEmpresaContato();
		cadastrarEmpresaContato.setId(contato.getEmpresa().getId());
		cadastrarEmpresaContato.setBairro(contato.getEmpresa().getBairro());
		cadastrarEmpresaContato.setCep(contato.getEmpresa().getCep());
		cadastrarEmpresaContato.setCidade(contato.getEmpresa().getCidade());
		cadastrarEmpresaContato.setCnpj(contato.getEmpresa().getCnpj());
		cadastrarEmpresaContato.setNumero(contato.getEmpresa().getNumero());
		cadastrarEmpresaContato.setRazaoSocial(contato.getEmpresa().getRazaoSocial());
		cadastrarEmpresaContato.setRua(contato.getEmpresa().getRua());
		cadastrarEmpresaContato.setUf(contato.getEmpresa().getUf());
		cadastrarEmpresaContato.setTipocontato(contato.getTipoContato());
		cadastrarEmpresaContato.setContato(contato.getContato());
		return cadastrarEmpresaContato;
	}
}
