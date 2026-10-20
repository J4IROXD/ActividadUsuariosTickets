package utez.edu.mx.ActividadUsuariosTickets.service;

import org.springframework.stereotype.Service;
import utez.edu.mx.ActividadUsuariosTickets.dto.TicketCreateDTO;
import utez.edu.mx.ActividadUsuariosTickets.exception.customExceptions.BadRequestException;
import utez.edu.mx.ActividadUsuariosTickets.model.EstadoTicket;
import utez.edu.mx.ActividadUsuariosTickets.model.PrioridadTicket;
import utez.edu.mx.ActividadUsuariosTickets.model.Ticket;
import utez.edu.mx.ActividadUsuariosTickets.model.Usuario;
import utez.edu.mx.ActividadUsuariosTickets.repository.TicketRepository;
import utez.edu.mx.ActividadUsuariosTickets.repository.UsuarioRepository;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository){
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Ticket crearTicket(TicketCreateDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new BadRequestException("El ticket no existe"));
        if (!usuario.getActivo()){
            throw new BadRequestException("El usuario no esta activo");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstado(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("El ticket no existe"));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("El ticket está CERRADO y no se puede modificar");
        }

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        }

        return ticketRepository.save(ticket);
    }

    public Ticket reasignarPrioridad(Long id, PrioridadTicket nuevaPrioridad) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("El ticket no existe"));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("No se puede cambiar la prioridad de un ticket CERRADO");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    public Usuario desactivarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new BadRequestException("El usuario no existe"));

        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new BadRequestException("El usuario no existe");
        }
        return ticketRepository.findByUsuarioId(usuarioId);
    }
}
