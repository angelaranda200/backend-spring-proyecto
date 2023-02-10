package pe.edu.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.idat.entity.HiloTicket;

public interface IHiloTicketRepo extends JpaRepository<HiloTicket, Long> {

}
