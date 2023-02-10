package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Prioridad;

public interface IPrioridad {
	
	public List<Prioridad> findAll();
	
	public Prioridad findByid(Long idPrioridad);
	
	public Prioridad save(Prioridad prioridad);
	
	public void delete(Long idPrioridad);

}
