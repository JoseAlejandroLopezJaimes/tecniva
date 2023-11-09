package com.example.tecnivaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tecnivaapp.entities.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
