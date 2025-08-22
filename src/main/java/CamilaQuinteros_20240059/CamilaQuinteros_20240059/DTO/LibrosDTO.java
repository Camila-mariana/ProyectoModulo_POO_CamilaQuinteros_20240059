package CamilaQuinteros_20240059.CamilaQuinteros_20240059.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class LibrosDTO {

    private Long id;

    @NotBlank //Validacion para que no permita dejar el campo en blanco
    private String titulo;

    @NotBlank
    private String isbn;

    private int año_publicacion;

    @NotBlank
    private String genero;

    @Positive(message = "Debe ser positivo") //Validacion para que el ID sea un numero positivo
    private Long id_autor;

}
