package pe.edu.idat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.AdjuntoTicket;

public interface AdjuntoTicketRepo extends JpaRepository<AdjuntoTicket, Long> {
	
	Optional<AdjuntoTicket> findByNombrefoto(String nombrefoto);

}
