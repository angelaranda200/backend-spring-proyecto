package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.Role;

public interface IRoleRepo extends JpaRepository<Role, Long> {

}
