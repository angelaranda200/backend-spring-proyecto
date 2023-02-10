package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Area;

public interface IDepartamentoService {
	
	public List<Area> findAll();
	
	public Area findById(Long idDepartamento);
	
	public Area save(Area departamento);
	
	public void delete(Long idDepartamento);

}
