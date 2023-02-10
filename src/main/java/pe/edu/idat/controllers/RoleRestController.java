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

import pe.edu.idat.entity.Role;
import pe.edu.idat.services.IRoleServices;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class RoleRestController {
	
	@Autowired
	private IRoleServices roleServices;
	
	@GetMapping("/roles")
	public List<Role> listar(){
		return roleServices.findAll();
	}
	
	@GetMapping("/roles/{idRole}")
	public ResponseEntity<?> findById(@PathVariable Long idRole){
		Role role = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			role = roleServices.findById(idRole);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(role == null) {
			response.put("mensaje", "El role ID:".concat(idRole.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Role>(role,HttpStatus.OK);
	}
	
	
	@PostMapping("/roles")
	public ResponseEntity<?> create(@Valid @RequestBody Role role, BindingResult result){
		
		Role roleNew = null;
		
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
			
			roleNew = roleServices.save(role);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El role ha sido creado con exito!");
		response.put("role", roleNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/roles/{idRole}")
	public ResponseEntity<?> update(@Valid @RequestBody Role role, BindingResult result,@PathVariable Long idRole){
		
		Role roleActual = roleServices.findById(idRole);
		Role roleUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (roleActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el role ID:"
					.concat(idRole.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			roleActual.setNombreRole(role.getNombreRole());
			
			
			roleUpdated = roleServices.save(roleActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el role en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El role ha sido actualizado con exito!");
		response.put("prioridad", roleUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/roles/{idRole}")
	public ResponseEntity<?> delete(@PathVariable Long idRole){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Role prioridad = roleServices.findById(idRole);

			roleServices.delete(idRole);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el role en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El role fue  eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
