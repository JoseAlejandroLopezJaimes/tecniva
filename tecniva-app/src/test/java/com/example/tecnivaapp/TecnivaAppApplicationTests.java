package com.example.tecnivaapp;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.tecnivaapp.entities.Usuario;
import com.example.tecnivaapp.service.UsuarioService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class TecnivaAppApplicationTests {

	private static final String PRODUCT_SERVICE_URL = "http://localhost:8080/usuarios";

	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateUsuario() {
		usuarioService.createUsuario(createObjectUsuario());
		assertTrue("usuario guardado correctamente", true);
	}

	@Test
	public void testPostUsuario() {
		Usuario usuario = createObjectUsuario();
		RestTemplate restTemplate = restTemplate();
		Usuario usuarioCreated = restTemplate.postForObject(PRODUCT_SERVICE_URL + "/create", usuario, Usuario.class);
		assertTrue(usuarioCreated != null);
	}

	@Test
	public void testGetUsuarioById() {
		Usuario usuario = createObjectUsuario();
		RestTemplate restTemplate = restTemplate();
		Usuario usuarioCreated = restTemplate.postForObject(PRODUCT_SERVICE_URL + "/create", usuario, Usuario.class);

		Usuario usuarioById = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/getUser/" + usuarioCreated.getId(),
				Usuario.class);
		assertTrue(usuarioById != null);
	}

	@Test
	public void testPutUsuario() {
		Usuario usuario = createObjectUsuario();
		RestTemplate restTemplate = restTemplate();
		Usuario usuarioCreated = restTemplate.postForObject(PRODUCT_SERVICE_URL + "/create", usuario, Usuario.class);
		usuarioCreated.setApellido("Update apellido");
		restTemplate.put(PRODUCT_SERVICE_URL + "/update/" + usuarioCreated.getId(), usuarioCreated, Usuario.class);
	}

	@Test
	public void testDeleteUsuario() {
		Usuario usuario = createObjectUsuario();
		RestTemplate restTemplate = restTemplate();
		Usuario usuarioCreated = restTemplate.postForObject(PRODUCT_SERVICE_URL + "/create", usuario, Usuario.class);

		restTemplate.delete(PRODUCT_SERVICE_URL + "/delete/" + usuarioCreated.getId(), Usuario.class);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUsuarios() {
		Usuario usuario = createObjectUsuario();
		RestTemplate restTemplate = restTemplate();
		restTemplate.postForObject(PRODUCT_SERVICE_URL + "/create", usuario, Usuario.class);

		List<Usuario> listUsuarios = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/all", List.class);
		assertTrue(listUsuarios.size() > 0);
	}
	
	
	private Usuario createObjectUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Nombre usuario de prueba");
		usuario.setApellido("Apellido de prueba");
		usuario.setEmail("josealejandrolopezjaimes@gmail.com");
		usuario.setFechaNacimiento(new Date());
		return usuario;
	}

	private RestTemplate restTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json, application/json"));
		converter.setObjectMapper(mapper);
		return new RestTemplate(Arrays.asList(converter));
	}

}
