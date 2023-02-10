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

import pe.edu.idat.entity.Tienda;
import pe.edu.idat.services.ITiendaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class TiendaRestController {
	
	@Autowired
	private ITiendaService tiendaService;
	
	@GetMapping("/tiendas")
	public List<Tienda> listar(){
		return tiendaService.findAll();
	}
	
	@GetMapping("/tiendas/{idTiendas}")
	public ResponseEntity<?> findById(@PathVariable Long idTiendas){
		Tienda tienda = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			tienda = tiendaService.findById(idTiendas);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(tienda == null) {
			response.put("mensaje", "La tienda ID:".concat(idTiendas.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Tienda>(tienda,HttpStatus.OK);
	}
	
	
	@PostMapping("/tiendas")
	public ResponseEntity<?> create(@Valid @RequestBody Tienda tienda, BindingResult result){
		
		Tienda tiendaNew = null;
		
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
			
			tiendaNew = tiendaService.save(tienda);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La tienda ha sido creado con exito!");
		response.put("tienda", tiendaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/tiendas/{idTiendas}")
	public ResponseEntity<?> update(@Valid @RequestBody Tienda tienda, BindingResult result,@PathVariable Long idTiendas){
		
		Tienda tiendaActual = tiendaService.findById(idTiendas);
		Tienda tiendaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (tiendaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la tienda ID:"
					.concat(idTiendas.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			tiendaActual.setNombre_tienda(tienda.getNombre_tienda());
			tiendaActual.setStatus(tienda.getStatus());
			tiendaActual.setIndicator(tienda.getIndicator());
			
			
			tiendaUpdated = tiendaService.save(tiendaActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tienda en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La tienda ha sido actualizado con exito!");
		response.put("tienda", tiendaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/tiendas/{idTiendas}")
	public ResponseEntity<?> delete(@PathVariable Long idTiendas){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Tienda tienda = tiendaService.findById(idTiendas);

			tiendaService.delete(idTiendas);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la tienda en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La tienda fue  eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
