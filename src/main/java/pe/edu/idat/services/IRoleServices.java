package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.Role;

public interface IRoleServices {
	
	public List<Role> findAll();
	
	public Role findById(Long idRole);
	
	public Role save(Role role);
	
	public void delete(Long idRole);

}
