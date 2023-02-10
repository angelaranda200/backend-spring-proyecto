package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Incidencia;

public interface IIncidenciaService {

	public List<Incidencia> findAll();
	
	public Incidencia findById(Long idIncidencia);
	
	public Incidencia save(Incidencia incidencia);
	
	public void delete(Long idIncidencia);
}
