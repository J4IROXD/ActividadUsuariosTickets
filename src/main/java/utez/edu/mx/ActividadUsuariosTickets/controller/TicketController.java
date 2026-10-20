package utez.edu.mx.ActividadUsuariosTickets.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.ActividadUsuariosTickets.dto.TicketCreateDTO;
import utez.edu.mx.ActividadUsuariosTickets.model.PrioridadTicket;
import utez.edu.mx.ActividadUsuariosTickets.model.Ticket;
import utez.edu.mx.ActividadUsuariosTickets.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("*")

public class TicketController {

        private final TicketService ticketService;

        public TicketController(TicketService ticketService) {
            this.ticketService = ticketService;
        }

        @PostMapping
        public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody TicketCreateDTO dto) {
            Ticket ticketCreado = ticketService.crearTicket(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketCreado);
        }

        @PatchMapping("/{id}/estado")
        public ResponseEntity<Ticket> actualizarEstado(@PathVariable Long id) {
            Ticket ticketActualizado = ticketService.actualizarEstado(id);
            return ResponseEntity.ok(ticketActualizado);
        }

        @PatchMapping("/{id}/prioridad")
        public ResponseEntity<Ticket> reasignarPrioridad(
                @PathVariable Long id,
                @RequestParam PrioridadTicket nuevaPrioridad) {
            Ticket ticketActualizado = ticketService.reasignarPrioridad(id, nuevaPrioridad);
            return ResponseEntity.ok(ticketActualizado);
        }

        @GetMapping("/usuario/{usuarioId}")
        public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
            List<Ticket> tickets = ticketService.obtenerTicketsUsuario(usuarioId);
            return ResponseEntity.ok(tickets);
        }
}
