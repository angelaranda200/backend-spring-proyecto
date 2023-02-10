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

import pe.edu.idat.entity.Area;
import pe.edu.idat.services.IDepartamentoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class AreaRestController {

	@Autowired
	private IDepartamentoService departamentoService;
	
	@GetMapping("/areas")
	public List<Area> listar(){
		return departamentoService.findAll();
	}
	
	@GetMapping("/areas/{idArea}")
	public ResponseEntity<?> findById(@PathVariable Long idArea){
		Area area = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			area = departamentoService.findById(idArea);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(area == null) {
			response.put("mensaje", "El area ID:".concat(idArea.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Area>(area,HttpStatus.OK);
	}
	
	
	@PostMapping("/areas")
	public ResponseEntity<?> create(@Valid @RequestBody Area departamento, BindingResult result){
		
		Area areaNew = null;
		
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
			
			areaNew = departamentoService.save(departamento);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El area ha sido creado con exito!");
		response.put("area", areaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/areas/{idArea}")
	public ResponseEntity<?> update(@Valid @RequestBody Area area, BindingResult result,@PathVariable Long idArea){
		
		Area areaActual = departamentoService.findById(idArea);
		Area areaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (areaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID:"
					.concat(idArea.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			areaActual.setNombre(area.getNombre());
			areaActual.setDescripcion(area.getDescripcion());
			areaActual.setEmail(area.getEmail());
			areaActual.setStatus(area.getStatus());
			areaActual.setIndicator(area.getIndicator());
			
			
			
			
			areaUpdated = departamentoService.save(areaActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el area en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El area ha sido actualizado con exito!");
		response.put("departamento", areaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/areas/{idArea}")
	public ResponseEntity<?> delete(@PathVariable Long idArea){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Area area = departamentoService.findById(idArea);

			departamentoService.delete(idArea);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el departamento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El area eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
