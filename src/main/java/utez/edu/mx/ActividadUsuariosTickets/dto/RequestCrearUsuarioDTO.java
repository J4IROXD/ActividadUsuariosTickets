package utez.edu.mx.ActividadUsuariosTickets.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestCrearUsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
