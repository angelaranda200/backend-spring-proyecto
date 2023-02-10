package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.Prioridad;

import pe.edu.idat.repository.IPrioridadRepo;

import pe.edu.idat.services.IPrioridad;

@Service
public class PrioridadServiceImpl implements IPrioridad{
	
	@Autowired
	private IPrioridadRepo prioridadRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Prioridad> findAll() {
		// TODO Auto-generated method stub
		return prioridadRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Prioridad findByid(Long idPrioridad) {
		// TODO Auto-generated method stub
		return prioridadRepo.findById(idPrioridad).orElse(null);
	}

	@Override
	@Transactional
	public Prioridad save(Prioridad prioridad) {
		// TODO Auto-generated method stub
		return prioridadRepo.save(prioridad);
	}

	@Override
	@Transactional
	public void delete(Long idPrioridad) {
		// TODO Auto-generated method stub
		prioridadRepo.deleteById(idPrioridad);
	}

	

}
