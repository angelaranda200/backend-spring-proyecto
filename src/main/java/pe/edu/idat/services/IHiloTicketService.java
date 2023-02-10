package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.HiloTicket;

public interface IHiloTicketService {

	public List<HiloTicket> findAll();
	
	public HiloTicket findById(Long IdHiloTicket);
	
	public HiloTicket save(HiloTicket hiloTicket);
	
	public HiloTicket saveHiloTicket(Long idTicket,HiloTicket hiloTicket);
	
}
