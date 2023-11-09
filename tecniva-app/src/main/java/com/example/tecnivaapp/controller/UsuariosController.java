package com.example.tecnivaapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tecnivaapp.entities.Usuario;
import com.example.tecnivaapp.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(path = "/all")
	public ResponseEntity<?> getAllUsuarios( @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		List<Usuario> listUsuarios = usuarioService.getAllUsuarios(pageNo,pageSize,sortBy);
 		return new ResponseEntity<>(listUsuarios,HttpStatus.OK);
	}
	
	@GetMapping(path = "/getUser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long idUsuario){
		Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(idUsuario);
		if(optionalUsuario.isEmpty()) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(optionalUsuario.get(),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario newUsuario){
		Usuario usuario = usuarioService.createUsuario(newUsuario);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable("id") Long idUpdate,@RequestBody Usuario editUsuario){
		Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(idUpdate);
		if(optionalUsuario.isEmpty()) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		usuarioService.updateUsuario(editUsuario);
		return new ResponseEntity<>(editUsuario,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeUsuario(@PathVariable("id") Long idUsuario){
		usuarioService.removeUsuario(idUsuario);
		return new ResponseEntity<>("Usuario Eliminado correctamente",HttpStatus.OK);
	}
}
