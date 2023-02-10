package pe.edu.idat.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.idat.entity.Usuarios;
import pe.edu.idat.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/usuarios")
	public List<Usuarios> listar(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/{idUsuario}")
	public ResponseEntity<?> findById(@PathVariable Long idUsuario){
		Usuarios usuario = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(idUsuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null) {
			response.put("mensaje", "El usuario ID:".concat(idUsuario.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuarios>(usuario,HttpStatus.OK);
	}
	
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@Valid @RequestBody Usuarios usuarios, BindingResult result){
		
		Usuarios usuarioNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			result.getFieldErrors();
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarios.setPassword(this.bCryptPasswordEncoder.encode(usuarios.getPassword()));
			usuarioNew = usuarioService.save(usuarios);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido creado con exito!");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/usuarios/{idUsuario}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuarios usuarios, BindingResult result,@PathVariable Long idUsuario){
		
		Usuarios usuarioActual = usuarioService.findById(idUsuario);
		Usuarios usuarioUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el usuario ID:"
					.concat(idUsuario.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			usuarioActual.setNombres(usuarios.getNombres());
			usuarioActual.setApellidos(usuarios.getApellidos());
			usuarioActual.setUsername(usuarios.getUsername());
			usuarioActual.setPassword(usuarios.getPassword());
			usuarioActual.setEmail(usuarios.getEmail());
			usuarioActual.setTelefono(usuarios.getTelefono());
			usuarioActual.setCelular(usuarios.getCelular());
			usuarioActual.setStatus(usuarios.getStatus());
			usuarioActual.setFecha_creado(usuarios.getFecha_creado());
			usuarioActual.setIndicator(usuarios.getIndicator());
			
			usuarioActual.setArea(usuarios.getArea());
			usuarioActual.setUsuarioRoles(usuarios.getUsuarioRoles());
			usuarioActual.setServicio(usuarios.getServicio());
			

			
			
			
			usuarioUpdated = usuarioService.save(usuarioActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido actualizado con exito!");
		response.put("departamento", usuarioUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{idUsuario}")
	public ResponseEntity<?> delete(@PathVariable Long idUsuario){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Usuarios usuario = usuarioService.findById(idUsuario);

			usuarioService.delete(idUsuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El usuario eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

}
