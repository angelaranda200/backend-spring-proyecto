package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.Area;
import pe.edu.idat.repository.IDepartamentoRepo;
import pe.edu.idat.services.IDepartamentoService;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{

	@Autowired
	private IDepartamentoRepo departamentoRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return departamentoRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Area findById(Long idDepartamento) {
		// TODO Auto-generated method stub
		return departamentoRepo.findById(idDepartamento).orElse(null);
	}

	@Override
	@Transactional
	public Area save(Area departamento) {
		// TODO Auto-generated method stub
		return departamentoRepo.save(departamento);
	}

	@Override
	@Transactional
	public void delete(Long idDepartamento) {
		// TODO Auto-generated method stub
		departamentoRepo.deleteById(idDepartamento);
		
	}
	
	
}
