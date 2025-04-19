package com.estudoSpring.deployNuvem.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudoSpring.deployNuvem.model.Usuario;
import com.estudoSpring.deployNuvem.service.IUsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {
	
	private final IUsuarioService usuarioService;
	
	public UsuarioController(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUserById(@PathVariable("id") Long id){
		return ResponseEntity.ok(usuarioService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
		var usuarioCriado = usuarioService.cadastrar(usuario);
		
		// constrói a URI do usuário recém-criado
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuarioCriado.getId()) // substitui {id} pelo ID real do usuário cadastrado
				.toUri();
		
		return ResponseEntity.created(location).body(usuarioCriado);
	}
}
