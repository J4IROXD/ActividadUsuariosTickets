package utez.edu.mx.ActividadUsuariosTickets.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.ActividadUsuariosTickets.dto.RequestCrearUsuarioDTO;
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

        @PostMapping
        public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody RequestCrearUsuarioDTO dto) {
        Usuario nuevoUsuario = ticketService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
