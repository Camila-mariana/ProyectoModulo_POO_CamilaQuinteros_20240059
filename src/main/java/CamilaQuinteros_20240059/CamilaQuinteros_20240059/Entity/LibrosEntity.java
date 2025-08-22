package CamilaQuinteros_20240059.CamilaQuinteros_20240059.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBLIBROS")
@Getter @Setter
@ToString @EqualsAndHashCode
public class LibrosEntity {

    //Aca definimos los campos y la tabla que se utilizaran
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro")
    @SequenceGenerator(name = "seq_libro , sequenceName = seq_libro", allocationSize = 1)

    private Long id;

    @Column (name = "TITULO") //aca escribimos justo como esta en nuestra base de datos
    private String titulo;

    @Column (name = "ISBN")
    private String isbn;

    @Column (name = "AÑO_PUBLICACION")
    private  int año_publicacion;

    @Column (name = "GENERO")
    private String genero;

    @Column (name = "ID_AUTOR")
    private Long id_autor;


}
