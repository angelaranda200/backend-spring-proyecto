package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.Servicio;
import pe.edu.idat.repository.IServicioRepo;
import pe.edu.idat.services.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService{
	
	
	@Autowired
	private IServicioRepo servicioRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> findAll() {
		// TODO Auto-generated method stub
		return servicioRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Servicio findById(Long idServicio) {
		// TODO Auto-generated method stub
		return servicioRepo.findById(idServicio).orElse(null);
	}

	@Override
	@Transactional
	public Servicio save(Servicio servicio) {
		// TODO Auto-generated method stub
		return servicioRepo.save(servicio);
	}

	@Override
	@Transactional
	public void delete(Long idServicio) {
		// TODO Auto-generated method stub
		servicioRepo.deleteById(idServicio);
	}

}
