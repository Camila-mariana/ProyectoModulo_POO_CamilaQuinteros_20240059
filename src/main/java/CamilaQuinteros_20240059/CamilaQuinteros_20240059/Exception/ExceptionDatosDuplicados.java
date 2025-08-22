package CamilaQuinteros_20240059.CamilaQuinteros_20240059.Exception;


import lombok.Getter;

public class ExceptionDatosDuplicados extends RuntimeException {

    //Agregamos el getter para poder utilizarlo en el controller
    @Getter
    private String campoDuplicado;

    public ExceptionDatosDuplicados(String message) {
        super(message);
    }

    public ExceptionDatosDuplicados(String message, String campoDuplicado) {
        super(message);
        this.campoDuplicado = campoDuplicado;
    }
}
