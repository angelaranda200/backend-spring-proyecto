package pe.edu.idat.servicesImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.HiloTicket;
import pe.edu.idat.entity.Ticket;
import pe.edu.idat.repository.ITicketRepo;
import pe.edu.idat.services.ITicketService;

@Service
public class TicketServicioImpl implements ITicketService{

	@Autowired
	private ITicketRepo ticketRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ticket findById(Long idTicket) {
		// TODO Auto-generated method stub
		return ticketRepo.findById(idTicket).orElse(null);
	}

	@Override
	@Transactional
	public Ticket save(Ticket ticket) {
		// TODO Auto-generated method stub
		
		return ticketRepo.save(ticket);
	}

	@Override
	@Transactional
	public void delete(Long idTicket) {
		// TODO Auto-generated method stub
		ticketRepo.deleteById(idTicket);
		
	}

}
