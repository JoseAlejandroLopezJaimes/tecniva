package com.example.tecnivaapp.service;

import java.util.List;
import java.util.Optional;

import com.example.tecnivaapp.entities.Usuario;

public interface UsuarioService {

	public List<Usuario> getAllUsuarios(Integer pageNo, Integer pageSize, String sortBy);

	public Optional<Usuario> getUsuarioById(Long idUsuario);

	public Usuario createUsuario(Usuario newUsuario);

	public void updateUsuario(Usuario editUsuario);

	public void removeUsuario(Long idUsuario);
}
