package utez.edu.mx.ActividadUsuariosTickets.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.ActividadUsuariosTickets.model.Usuario;
import utez.edu.mx.ActividadUsuariosTickets.service.TicketService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

        private final TicketService ticketService;

        public UsuarioController(TicketService ticketService) {
            this.ticketService = ticketService;
        }

        @PatchMapping("/{id}/desactivar")
        public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {
            Usuario usuarioDesactivado = ticketService.desactivarUsuario(id);
            return ResponseEntity.ok(usuarioDesactivado);
        }
}
