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

import pe.edu.idat.entity.Incidencia;
import pe.edu.idat.services.IIncidenciaService;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class IncidenciaRestController {
	
	@Autowired
	private IIncidenciaService incidenciaService;
	
	@GetMapping("/incidencias")
	public List<Incidencia> listar(){
		return incidenciaService.findAll();
	}
	
	@GetMapping("/incidencias/{idIncidencias}")
	public ResponseEntity<?> findById(@PathVariable Long idIncidencias){
		Incidencia incidencia = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			incidencia = incidenciaService.findById(idIncidencias);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(incidencia == null) {
			response.put("mensaje", "La incidencia ID:".concat(idIncidencias.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Incidencia>(incidencia,HttpStatus.OK);
	}
	
	
	@PostMapping("/incidencias")
	public ResponseEntity<?> create(@Valid @RequestBody Incidencia incidencia, BindingResult result){
		
		Incidencia incidenciaNew = null;
		
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
			
			incidenciaNew = incidenciaService.save(incidencia);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La incidencia ha sido creado con exito!");
		response.put("incidencia", incidenciaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/incidencias/{idIncidencias}")
	public ResponseEntity<?> update(@Valid @RequestBody Incidencia incidencia, BindingResult result,@PathVariable Long idIncidencias){
		
		Incidencia incidenciaActual = incidenciaService.findById(idIncidencias);
		Incidencia incidenciaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (incidenciaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID:"
					.concat(idIncidencias.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			incidenciaActual.setNombre(incidencia.getNombre());
			incidenciaActual.setDescripcion(incidencia.getDescripcion());
			incidenciaActual.setStatus(incidencia.getStatus());
			incidenciaActual.setIndicator(incidencia.getIndicator());
			incidenciaActual.setPlan_sla(incidencia.getPlan_sla());
			
			
			incidenciaUpdated = incidenciaService.save(incidenciaActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la incidencia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La incidencia ha sido actualizado con exito!");
		response.put("cliente", incidenciaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/incidencias/{idIncidencias}")
	public ResponseEntity<?> delete(@PathVariable Long idIncidencias){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Incidencia incidencia = incidenciaService.findById(idIncidencias);

			incidenciaService.delete(idIncidencias);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la incidencia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La incidencia fue  eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}
