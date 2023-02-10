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

import pe.edu.idat.entity.Prioridad;
import pe.edu.idat.services.IPrioridad;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class PrioridadRestController {
	
	@Autowired
	private IPrioridad prioridadrepo;
	
	@GetMapping("/prioridad")
	public List<Prioridad> listar(){
		return prioridadrepo.findAll();
	}
	
	@GetMapping("/prioridad/{idPrioridad}")
	public ResponseEntity<?> findById(@PathVariable Long idPrioridad){
		Prioridad prioridad = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			prioridad = prioridadrepo.findByid(idPrioridad);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(prioridad == null) {
			response.put("mensaje", "La prioridad ID:".concat(idPrioridad.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Prioridad>(prioridad,HttpStatus.OK);
	}
	
	
	@PostMapping("/prioridad")
	public ResponseEntity<?> create(@Valid @RequestBody Prioridad prioridad, BindingResult result){
		
		Prioridad prioridadNew = null;
		
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
			
			prioridadNew = prioridadrepo.save(prioridad);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La prioridad ha sido creado con exito!");
		response.put("prioridad", prioridadNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/prioridad/{idPrioridad}")
	public ResponseEntity<?> update(@Valid @RequestBody Prioridad prioridad, BindingResult result,@PathVariable Long idPrioridad){
		
		Prioridad prioridadActual = prioridadrepo.findByid(idPrioridad);
		Prioridad prioridadUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (prioridadActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la prioridad ID:"
					.concat(idPrioridad.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			prioridadActual.setNombre(prioridad.getNombre());
			prioridadActual.setDescripcion(prioridad.getDescripcion());
			prioridadActual.setStatus(prioridad.getStatus());
			prioridadActual.setIndicator(prioridad.getIndicator());
			
			
			prioridadUpdated = prioridadrepo.save(prioridadActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la prioridad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La prioridad ha sido actualizado con exito!");
		response.put("prioridad", prioridadUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/prioridad/{idPrioridad}")
	public ResponseEntity<?> delete(@PathVariable Long idPrioridad){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Prioridad prioridad = prioridadrepo.findByid(idPrioridad);

			prioridadrepo.delete(idPrioridad);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la prioridad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La prioridad fue  eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
