package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.EstadoTicket;
import pe.edu.idat.repository.IEstadoTicketRepo;
import pe.edu.idat.services.IEstadoTicketService;

@Service
public class EstadoTicketServiceImpl implements IEstadoTicketService{
	
	@Autowired
	private IEstadoTicketRepo estadoTicketRepo;

	@Override
	@Transactional(readOnly = true)
	public List<EstadoTicket> findAll() {
		// TODO Auto-generated method stub
		return estadoTicketRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EstadoTicket findById(Long idEstadoTicket) {
		// TODO Auto-generated method stub
		return estadoTicketRepo.findById(idEstadoTicket).orElse(null);
	}

	@Override
	@Transactional
	public EstadoTicket save(EstadoTicket estadoTicket) {
		// TODO Auto-generated method stub
		return estadoTicketRepo.save(estadoTicket);
	}

	@Override
	@Transactional
	public void delete(Long idEstadoTicket) {
		// TODO Auto-generated method stub
		estadoTicketRepo.deleteById(idEstadoTicket);
	}

	@Override
	public EstadoTicket getByNombre(String nombre) {
		// TODO Auto-generated method stub
		return estadoTicketRepo.findByNombre(nombre);
	}

}
