package utez.edu.mx.ActividadUsuariosTickets.exception.customExceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
