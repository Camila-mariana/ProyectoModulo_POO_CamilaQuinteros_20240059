package CamilaQuinteros_20240059.CamilaQuinteros_20240059.Controller;

import CamilaQuinteros_20240059.CamilaQuinteros_20240059.DTO.LibrosDTO;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Exception.ExceptionDatosDuplicados;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Exception.ExceptionLibroNoEncontrado;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Service.LibrosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    @Autowired
    LibrosService service;

    @GetMapping("/obtenerLibros")
    public List<LibrosDTO> obtenerLibros(){
        return service.obtenerLibros();
    }

    //Metodo para buscar por ID
    @GetMapping("/buscarLibro/{id}")
    public LibrosDTO buscarLibro (@PathVariable Long id){ //aqui usamos el pathvariable para a la hora de probar la url reconozca el id
        return service.buscarLibro(id);
    }


    @PostMapping("/insertarLibro")
    public ResponseEntity<?> libroGuardado (@Valid @RequestBody LibrosDTO json, HttpServletRequest request){
        try {
            LibrosDTO respuesta = service.insertarLibro(json);
            if (respuesta == null) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "Incersion fallida",
                "errorType","VALIDATION_ERROR",
                "message", "Datos no registrados"
            ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "succes",
                    "data", respuesta
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "Error no controlado al registrar",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping ("/actualizarLibro/{id}")
    public ResponseEntity <?> modificarLibro(@PathVariable Long id, @Valid @RequestBody LibrosDTO json, BindingResult bindingResult){

        //Aqui validamos tanto el ID como los datos que se quieren actualizar

        if (bindingResult.hasErrors()){
            Map <String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }try {
            LibrosDTO dto = service.actualizarLibro(id, json);
            return ResponseEntity.ok(dto);
        }catch (ExceptionLibroNoEncontrado e){
            return ResponseEntity.notFound().build();
        }catch (ExceptionDatosDuplicados e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos Duplicados",
                    "campo", e.getCampoDuplicado() //aca utilizamos el get que agregamos en la exception
            ));
        }
    }

}
