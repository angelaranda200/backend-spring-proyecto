package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Ticket;

public interface ITicketService {
	
	public List<Ticket> findAll();
	
	public Ticket findById(Long idTicket);
	
	public Ticket save(Ticket ticket);
	
	public void delete(Long idTicket);

}
