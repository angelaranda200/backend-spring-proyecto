package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Servicio;

public interface IServicioService {
	
	public List<Servicio> findAll();
	
	public Servicio findById(Long idServicio);
	
	public Servicio save(Servicio servicio);
	
	public void delete(Long idServicio);

}
