package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.Usuarios;

public interface IUsuarioRepo extends JpaRepository<Usuarios, Long> {
	
	public Usuarios findByUsername(String username);

}
