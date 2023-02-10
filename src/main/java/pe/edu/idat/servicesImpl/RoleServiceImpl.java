package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.idat.entity.Role;
import pe.edu.idat.repository.IRoleRepo;
import pe.edu.idat.services.IRoleServices;

@Service
public class RoleServiceImpl implements IRoleServices{
	
	@Autowired
	private IRoleRepo roleRepo;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	@Override
	public Role findById(Long idRole) {
		// TODO Auto-generated method stub
		return roleRepo.findById(idRole).orElse(null);
	}

	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		return roleRepo.save(role);
	}

	@Override
	public void delete(Long idRole) {
		// TODO Auto-generated method stub
		roleRepo.deleteById(idRole);
	}

}
