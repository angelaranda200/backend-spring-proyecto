package pe.edu.idat.services;

import java.util.List;
import java.util.Set;

import pe.edu.idat.entity.Role;
import pe.edu.idat.entity.UsuarioRol;
import pe.edu.idat.entity.Usuarios;

public interface IUsuarioService {
	
	public List<Usuarios> findAll();
	
	public Usuarios findById(Long idPersonal);
	
	public Usuarios save(Usuarios personal);
	
	public void delete(Long idPersonal);
}
