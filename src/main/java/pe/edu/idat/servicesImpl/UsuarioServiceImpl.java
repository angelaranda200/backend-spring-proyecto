package pe.edu.idat.servicesImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.Role;
import pe.edu.idat.entity.UsuarioRol;
import pe.edu.idat.entity.Usuarios;
import pe.edu.idat.repository.IRoleRepo;
import pe.edu.idat.repository.IUsuarioRepo;
import pe.edu.idat.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepo iPersonalRepo;
	
	@Autowired
	private IRoleRepo iRoleRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> findAll() {
		// TODO Auto-generated method stub
		return iPersonalRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuarios findById(Long idPersonal) {
		// TODO Auto-generated method stub
		return iPersonalRepo.findById(idPersonal).orElse(null);
	}

	@Override
	@Transactional
	public Usuarios save(Usuarios personal) {
		// TODO Auto-generated method stub
		return iPersonalRepo.save(personal);
	}

	@Override
	@Transactional
	public void delete(Long idPersonal) {
		// TODO Auto-generated method stub
		iPersonalRepo.deleteById(idPersonal);
	}

}
