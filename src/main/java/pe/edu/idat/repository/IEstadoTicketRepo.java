package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.EstadoTicket;

public interface IEstadoTicketRepo extends JpaRepository<EstadoTicket, Long> {
	
	EstadoTicket findByNombre(String nombre);

}
