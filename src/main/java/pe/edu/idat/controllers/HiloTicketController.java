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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.idat.entity.HiloTicket;
import pe.edu.idat.services.IHiloTicketService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class HiloTicketController {

	@Autowired
	private IHiloTicketService hiloTicketService;

	@GetMapping("/hilosTicket")
	public List<HiloTicket> listar() {
		return hiloTicketService.findAll();
	}

	@GetMapping("/hilosTicket/{idHiloTicket}")
	public ResponseEntity<?> findById(@PathVariable Long idHiloTicket) {
		HiloTicket hiloTicket = null;

		Map<String, Object> response = new HashMap<>();

		try {
			hiloTicket = hiloTicketService.findById(idHiloTicket);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (hiloTicket == null) {
			response.put("mensaje",
					"El hilo Ticket ID:".concat(idHiloTicket.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HiloTicket>(hiloTicket, HttpStatus.OK);
	}
	
	@PostMapping("/tickets/{idTickets}/hilosTicket")
	public ResponseEntity<?> crearHiloTicket(@PathVariable Long idTicket,@RequestBody HiloTicket hiloTicket){
		HiloTicket hiloTicketNew = null;

		Map<String, Object> response = new HashMap<>();
		
		try {

			hiloTicketNew = hiloTicketService.saveHiloTicket(idTicket,hiloTicket);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El hilo ticket ha sido creado con exito!");
		response.put("area", hiloTicketNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

	@PostMapping("/hilosTicket")
	public ResponseEntity<?> create(@Valid @RequestBody HiloTicket hiloTicket, BindingResult result) {
		HiloTicket hiloTicketNew = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			hiloTicketNew = hiloTicketService.save(hiloTicket);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El hilo ticket ha sido creado con exito!");
		response.put("area", hiloTicketNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("//hilosTicket/{idHiloTicket}")
	public ResponseEntity<?> update(@Valid @RequestBody HiloTicket hiloTicket, BindingResult result,@PathVariable Long idHiloTicket){
		HiloTicket hiloTicketActual=hiloTicketService.findById(idHiloTicket);
		HiloTicket hiloTicketUpdate=null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (hiloTicketActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID:"
					.concat(idHiloTicket.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			hiloTicketActual.setContenido(hiloTicket.getContenido());
			hiloTicketActual.setFecha_creacion(hiloTicket.getFecha_creacion());
			//hiloTicketActual.setTickets(hiloTicket.getTickets());
			hiloTicketActual.setUsuario(hiloTicket.getUsuario());
			
			hiloTicketUpdate= hiloTicketService.save(hiloTicketActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el area en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con exito!");
		response.put("hiloTicket", hiloTicketUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
}
