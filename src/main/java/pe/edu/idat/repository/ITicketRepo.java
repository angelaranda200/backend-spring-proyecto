package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.Ticket;

public interface ITicketRepo extends JpaRepository<Ticket, Long> {

}
