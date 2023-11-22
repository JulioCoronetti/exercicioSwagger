package com.senai.Lombok.PrjLombok.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.Lombok.PrjLombok.entities.Usuario;
import com.senai.Lombok.PrjLombok.repositories.UsuarioRepositorio;

@Service
public class UsuarioService {
	private final UsuarioRepositorio userRepository;

	@Autowired
	public UsuarioService (UsuarioRepositorio userRepository) {
		this.userRepository = userRepository;
	}

	public Usuario findUsuarioById(Long id) {
		Optional<Usuario> Usuario = userRepository.findById(id);
		return Usuario.orElse(null);
	}

	public List<Usuario> findAllUsuario() {
		return userRepository.findAll();
	}

	public Usuario insertUsuario(Usuario usuario) {
		return userRepository.save(usuario);
	}

	public Usuario updateUsuario(Long id, Usuario novoUsuario) {
		Optional<Usuario> usuarioOptional = userRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			Usuario usuarioExistente = usuarioOptional.get();
			usuarioExistente.setNome(novoUsuario.getNome());
			usuarioExistente.setEmail(novoUsuario.getEmail());
			return userRepository.save(usuarioExistente);
		} else {
			return null;
		}
	}

	public boolean deleteUsuario(Long id) {
		Optional<Usuario> usuarioExistente = userRepository.findById(id);
		if (usuarioExistente.isPresent()) {
			userRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}


}
