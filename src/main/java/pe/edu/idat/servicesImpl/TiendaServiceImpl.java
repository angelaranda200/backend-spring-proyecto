package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.Tienda;
import pe.edu.idat.repository.ITiendaRepo;
import pe.edu.idat.services.ITiendaService;

@Service
public class TiendaServiceImpl implements ITiendaService{

	@Autowired 
	private ITiendaRepo tiendaRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Tienda> findAll() {
		// TODO Auto-generated method stub
		return tiendaRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tienda findById(Long idTienda) {
		// TODO Auto-generated method stub
		return tiendaRepo.findById(idTienda).orElse(null);
	}

	@Override
	@Transactional
	public Tienda save(Tienda tienda) {
		// TODO Auto-generated method stub
		return tiendaRepo.save(tienda);
	}

	@Override
	@Transactional
	public void delete(Long idTienda) {
		// TODO Auto-generated method stub
		tiendaRepo.deleteById(idTienda);
	}
	
}
