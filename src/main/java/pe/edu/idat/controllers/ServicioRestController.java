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

import pe.edu.idat.entity.Servicio;
import pe.edu.idat.services.IServicioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ServicioRestController {
	
	@Autowired
	private IServicioService servicioService;
	
	@GetMapping("/servicios")
	public List<Servicio> listar(){
		return servicioService.findAll();
	}
	
	@GetMapping("/servicios/{idServicios}")
	public ResponseEntity<?> findById(@PathVariable Long idServicios){
		Servicio servicio = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio = servicioService.findById(idServicios);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(servicio == null) {
			response.put("mensaje", "El servicio ID:".concat(idServicios.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Servicio>(servicio,HttpStatus.OK);
	}
	
	
	@PostMapping("/servicios")
	public ResponseEntity<?> create(@Valid @RequestBody Servicio servicio, BindingResult result){
		
		Servicio servicioNew = null;
		
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
			
			servicioNew = servicioService.save(servicio);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El servicio ha sido creado con exito!");
		response.put("servicio", servicioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/servicios/{idServicios}")
	public ResponseEntity<?> update(@Valid @RequestBody Servicio servicio, BindingResult result,@PathVariable Long idServicios){
		
		Servicio servicioActual = servicioService.findById(idServicios);
		Servicio servicioUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (servicioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el servicio ID:"
					.concat(idServicios.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			servicioActual.setNombre(servicio.getNombre());
			servicioActual.setDescripcion(servicio.getDescripcion());
			servicioActual.setStatus(servicio.getStatus());
			servicioActual.setIndicator(servicio.getIndicator());
			
			
			servicioUpdated = servicioService.save(servicioActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el servicio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El servicio ha sido actualizado con exito!");
		response.put("servicio", servicioUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/servicios/{idServicios}")
	public ResponseEntity<?> delete(@PathVariable Long idServicios){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Servicio servicio = servicioService.findById(idServicios);

			servicioService.delete(idServicios);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el servicio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El servicio fue  eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
