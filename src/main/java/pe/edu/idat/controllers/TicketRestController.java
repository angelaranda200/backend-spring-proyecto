package pe.edu.idat.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.idat.entity.AdjuntoTicket;
import pe.edu.idat.entity.EstadoTicket;
import pe.edu.idat.entity.Ticket;
import pe.edu.idat.repository.AdjuntoTicketRepo;
import pe.edu.idat.services.ITicketService;
import pe.edu.idat.services.IUploadService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class TicketRestController {
	
	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private AdjuntoTicketRepo adjuntoTicketRepo;
	
	
	@GetMapping("/tickets")
	public List<Ticket> listar(){
		return ticketService.findAll();
	}
	
	@GetMapping("/tickets/{idTickets}")
	public ResponseEntity<?> findById(@PathVariable Long idTickets){
		Ticket ticket = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			ticket = ticketService.findById(idTickets);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ticket == null) {
			response.put("mensaje", "El ticket ID:".concat(idTickets.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
	}
	
	
	
	@PostMapping(value={"/tickets"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> create(@Valid @RequestPart("ticket") Ticket ticket,@RequestParam(name = "imagen",required = false) MultipartFile[] imagen, 
			BindingResult result){
		
		Ticket ticketNew = null;
		
		
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
			
			try {
				if(imagen==null) {
					ticket.setAdjuntotickets(null);
				}else {
					Set<AdjuntoTicket> fotos = uploadImage(imagen);
					ticket.setAdjuntotickets(fotos);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*EstadoTicket estadoTickets = new EstadoTicket();
			estadoTickets.setIdEstadoTicket(4L);
			estadoTickets.setNombre("ABIERTO");
			estadoTickets.setDescripcion("Abierto");
			estadoTickets.setStatus("Ok");
			estadoTickets.setIndicator("si");
			ticket.setEstado_ticket(estadoTickets);*/
			ticketNew = ticketService.save(ticket);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El ticket ha sido creado con exito!");
		response.put("ticket", ticketNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value={"/tickets"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> update(@Valid @RequestPart("ticket") Ticket ticket,@RequestParam(name = "imagen",required = false) MultipartFile[] imagen, 
			BindingResult result){
		
		Ticket ticketNew = null;
		
		
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
			
			try {
				if(imagen==null) {
					ticket.setAdjuntotickets(null);
				}else {
					Set<AdjuntoTicket> fotos = uploadImage(imagen);
					ticket.setAdjuntotickets(fotos);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El ticket ha sido creado con exito!");
		response.put("ticket", ticketNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	public Set<AdjuntoTicket> uploadImage(MultipartFile[] multipartFiles)throws IOException {
		Set<AdjuntoTicket> adjuntoTickets = new HashSet<>();
		
		for(MultipartFile file: multipartFiles) {
			AdjuntoTicket adjuntoTicket = new AdjuntoTicket(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes()
					);
			adjuntoTickets.add(adjuntoTicket);
		}
		
		return adjuntoTickets;
	}
	
	/*@PutMapping("/tickets/{idTickets}")
	public ResponseEntity<?> update(@Valid @RequestBody Ticket ticket, BindingResult result,@PathVariable Long idTickets){
		
		Ticket ticketActual = ticketService.findById(idTickets);
		Ticket ticketUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			result.getFieldErrors();
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if (ticketActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el ticket ID:"
					.concat(idTickets.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			ticketActual.setVencido(ticket.getVencido());
			ticketActual.setDescripcion(ticket.getDescripcion());
			ticketActual.setFecha_creacion(ticket.getFecha_creacion());
			ticketActual.setFecha_vencimiento(ticket.getFecha_vencimiento());
			ticketActual.setFecha_cierre(ticket.getFecha_cierre());
			ticketActual.setFecha_actualizacion(ticket.getFecha_actualizacion());
			ticketActual.setUltimo_mensaje(ticket.getUltimo_mensaje());
			ticketActual.setUltima_respuesta(ticket.getUltima_respuesta());
			ticketActual.setNumero(ticket.getNumero());
			ticketActual.setUsuario(ticket.getUsuario());
			ticketActual.setEstado_ticket(ticket.getEstado_ticket());
			ticketActual.setPrioridad(ticket.getPrioridad());
			ticketActual.setIncidencia(ticket.getIncidencia());
			ticketActual.setArea(ticket.getArea());
			ticketActual.setTienda(ticket.getTienda());
			
			ticketUpdated = ticketService.save(ticketActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el ticket en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El ticket ha sido actualizado con exito!");
		response.put("ticket", ticketUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}*/
	
	@DeleteMapping("/tickets/{idTickets}")
	public ResponseEntity<?> delete(@PathVariable Long idTickets){
		Map<String, Object> response = new HashMap<>();

		try {
			@SuppressWarnings("unused")
			Ticket ticket = ticketService.findById(idTickets);

			ticketService.delete(idTickets);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el ticket en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El ticket fue  eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/solicitud/{nombrefoto}")
	public ResponseEntity<Resource> downloadAdjuntoTicket(@PathVariable String nombrefoto){
		
		
		
		AdjuntoTicket adjuntoTicket = null;
		//byte[] imageData=downloadImage(nombrefoto);
		/*Optional<AdjuntoTicket> dbAdjuntoTicket = adjuntoTicketRepo.findByNombrefoto(nombrefoto);
		byte[] images = dbAdjuntoTicket.get().getPicByte();
		
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +dbAdjuntoTicket.get().getNombrefoto()+ "");
		
		return ResponseEntity.status(HttpStatus.OK)
					.headers(cabecera)
					.contentType(MediaType.valueOf("image/jpeg"))
					.body(images);*/
		adjuntoTicket = adjuntoTicketRepo.findByNombrefoto(nombrefoto).orElse(null);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(adjuntoTicket.getTipo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +adjuntoTicket.getNombrefoto()+ "\"")
				.body(new ByteArrayResource(adjuntoTicket.getPicByte()));
				
	}
	
	public byte[] downloadImage(String nombrefoto) {
		Optional<AdjuntoTicket> dbAdjuntoTicket = adjuntoTicketRepo.findByNombrefoto(nombrefoto);
		byte[] images = dbAdjuntoTicket.get().getPicByte();
		return images;
	}
	
	

}
