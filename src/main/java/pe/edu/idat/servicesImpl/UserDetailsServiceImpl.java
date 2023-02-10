package pe.edu.idat.servicesImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.idat.entity.Authority;
import pe.edu.idat.entity.Usuarios;
import pe.edu.idat.repository.IUsuarioRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepo usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios user = this.usuarioRepo.findByUsername(username);
		if(user == null) {
			System.out.println("Usuario no encontrado");
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return user;
		
	}
	

}
