package com.estudoSpring.deployNuvem.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.estudoSpring.deployNuvem.model.Usuario;
import com.estudoSpring.deployNuvem.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	private final UsuarioRepository usuarioRepository; 
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(NoSuchElementException::new) ;
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		if(usuarioRepository.existsByContaNumero(usuario.getConta().getNumero())) {
			throw new IllegalArgumentException("Usuário já existente com esse número de conta");
		}
		
		return usuarioRepository.save(usuario);
	}

}
