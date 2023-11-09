package com.example.tecnivaapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.tecnivaapp.dao.UsuarioRepository;
import com.example.tecnivaapp.entities.Usuario;
import com.example.tecnivaapp.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getAllUsuarios(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Usuario> pagedResult = usuarioRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Usuario>();
		}
	}

	@Override
	public Optional<Usuario> getUsuarioById(Long idUsuario) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
		return optionalUsuario;
	}

	@Override
	public Usuario createUsuario(Usuario newUsuario) {
		Usuario usuario = usuarioRepository.save(newUsuario);
		return usuario;
	}

	@Override
	public void updateUsuario(Usuario editUsuario) {
		usuarioRepository.save(editUsuario);
	}

	@Override
	public void removeUsuario(Long idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}
}
