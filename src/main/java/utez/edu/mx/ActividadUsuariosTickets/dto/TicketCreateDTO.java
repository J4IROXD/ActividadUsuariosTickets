package utez.edu.mx.ActividadUsuariosTickets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import utez.edu.mx.ActividadUsuariosTickets.model.PrioridadTicket;

@Data
public class TicketCreateDTO {
    @NotBlank(message = "La descripción no puede estar vacia")
    private String descripcion;

    @NotNull(message = "Debe de elegir una prioridad")
    private PrioridadTicket prioridad;

    @NotNull(message = "Debe de escribir el ID del usuario")
    private Long usuarioId;
}
