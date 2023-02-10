package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.EstadoTicket;

public interface IEstadoTicketService {
	
	public List<EstadoTicket> findAll();
	
	public EstadoTicket findById(Long idEstadoTicket);
	
	public EstadoTicket save(EstadoTicket estadoTicket);
	
	public void delete(Long idEstadoTicket);
	
	public EstadoTicket getByNombre(String nombre);

}
