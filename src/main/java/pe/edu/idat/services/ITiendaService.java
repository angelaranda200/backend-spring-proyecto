package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Tienda;

public interface ITiendaService {
	
	public List<Tienda> findAll();
	
	public Tienda findById(Long idTienda);
	
	public Tienda save(Tienda tienda);
	
	public void delete(Long idTienda);

}
