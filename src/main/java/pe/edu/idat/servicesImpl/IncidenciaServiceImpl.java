package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.Incidencia;
import pe.edu.idat.repository.IIncidenciaRepo;
import pe.edu.idat.services.IIncidenciaService;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService{

	@Autowired
	private IIncidenciaRepo iIncidenciaRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Incidencia> findAll() {
		// TODO Auto-generated method stub
		return iIncidenciaRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Incidencia findById(Long idIncidencia) {
		// TODO Auto-generated method stub
		return iIncidenciaRepo.findById(idIncidencia).orElse(null);
	}

	@Override
	@Transactional
	public Incidencia save(Incidencia incidencia) {
		// TODO Auto-generated method stub
		return iIncidenciaRepo.save(incidencia);
	}

	@Override
	@Transactional
	public void delete(Long idIncidencia) {
		// TODO Auto-generated method stub
		iIncidenciaRepo.deleteById(idIncidencia);
		
	}
	
	
}
