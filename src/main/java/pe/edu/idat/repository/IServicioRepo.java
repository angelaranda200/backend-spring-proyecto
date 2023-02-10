package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.Servicio;

public interface IServicioRepo extends JpaRepository<Servicio, Long> {

}
