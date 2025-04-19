package com.estudoSpring.deployNuvem.service;

import com.estudoSpring.deployNuvem.model.Usuario;

public interface IUsuarioService {

	Usuario findById(Long id);
	
	Usuario cadastrar(Usuario usuario);
}
