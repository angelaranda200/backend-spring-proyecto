package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.idat.entity.HiloTicket;
import pe.edu.idat.entity.Ticket;
import pe.edu.idat.repository.IHiloTicketRepo;
import pe.edu.idat.repository.ITicketRepo;
import pe.edu.idat.services.IHiloTicketService;

@Service
public class HiloTicketServiceImpl implements IHiloTicketService{

	@Autowired
	private IHiloTicketRepo hiloTicketRepo;
	
	@Autowired
	private ITicketRepo ticketRepo;
	
	@Override
	public List<HiloTicket> findAll() {
		// TODO Auto-generated method stub
		return hiloTicketRepo.findAll();
	}

	@Override
	public HiloTicket save(HiloTicket hiloTicket) {
		// TODO Auto-generated method stub
		return hiloTicketRepo.save(hiloTicket);
	}

	@Override
	public HiloTicket findById(Long IdHiloTicket) {
		// TODO Auto-generated method stub
		return hiloTicketRepo.findById(IdHiloTicket).orElse(null);
	}

	@Override
	public HiloTicket saveHiloTicket(Long idTicket, HiloTicket hiloTicket) {
		
		//HiloTicket ht= new HiloTicket();
		
		/*Ticket ticket = this.ticketRepo.findById(idTicket).orElse(null);
		hiloTicket.setTickets(ticket);*/
		return hiloTicketRepo.save(hiloTicket);
	}

}
