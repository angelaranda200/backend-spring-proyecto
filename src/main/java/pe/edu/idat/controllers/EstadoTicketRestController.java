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

import pe.edu.idat.entity.EstadoTicket;
import pe.edu.idat.services.IEstadoTicketService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class EstadoTicketRestController {
	
	@Autowired
	private IEstadoTicketService estadoTicketService;
	
	@GetMapping("/estadostickets")
	public List<EstadoTicket> listar(){
		return estadoTicketService.findAll();
	}
	
	@GetMapping("/estadostickets/{idEstadosTickets}")
	public ResponseEntity<?> findById(@PathVariable Long idEstadosTickets){
		EstadoTicket estadoTicket = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			estadoTicket = estadoTicketService.findById(idEstadosTickets);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(estadoTicket == null) {
			response.put("mensaje", "El estado de ticket ID:".concat(idEstadosTickets.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EstadoTicket>(estadoTicket,HttpStatus.OK);
	}
	
	
	@PostMapping("/estadostickets")
	public ResponseEntity<?> create(@Valid @RequestBody EstadoTicket estadoTicket, BindingResult result){
		
		EstadoTicket estadoTicketNew = null;
		
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
			
			estadoTicketNew = estadoTicketService.save(estadoTicket);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estado del ticket ha sido creado con exito!");
		response.put("estadoTicket", estadoTicketNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/estadostickets/{idEstadosTickets}")
	public ResponseEntity<?> update(@Valid @RequestBody EstadoTicket estadoTicket, BindingResult result,@PathVariable Long idEstadosTickets){
		
		EstadoTicket estadoTicketActual = estadoTicketService.findById(idEstadosTickets);
		EstadoTicket estadoTicketUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (estadoTicketActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID:"
					.concat(idEstadosTickets.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			estadoTicketActual.setNombre(estadoTicket.getNombre());
			estadoTicketActual.setDescripcion(estadoTicket.getDescripcion());
			estadoTicketActual.setStatus(estadoTicket.getStatus());
			estadoTicketActual.setIndicator(estadoTicket.getIndicator());
			
			
			
			estadoTicketUpdated = estadoTicketService.save(estadoTicketActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estado del ticket en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estado del ticket ha sido actualizado con exito!");
		response.put("estadoTicket", estadoTicketUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/estadostickets/{idEstadosTickets}")
	public ResponseEntity<?> delete(@PathVariable Long idEstadosTickets){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			EstadoTicket estadoTicket = estadoTicketService.findById(idEstadosTickets);

			estadoTicketService.delete(idEstadosTickets);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el estado del ticket en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El estado del ticekt fue eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
