package CamilaQuinteros_20240059.CamilaQuinteros_20240059.Service;

import CamilaQuinteros_20240059.CamilaQuinteros_20240059.DTO.LibrosDTO;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Entity.LibrosEntity;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Exception.ExceptionLibroNoEncontrado;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Exception.ExceptionLibroNoRegistrado;
import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Repository.LibrosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LibrosService {

    @Autowired
    LibrosRepository repo;

    public List<LibrosDTO> obtenerLibros(){
        List<LibrosEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public LibrosDTO buscarLibro(Long id){
        if (repo.existsById(id)){
            LibrosEntity entity = repo.getReferenceById(id); //Aca se indica que se utilizara el id como referencia al buscar
            return new LibrosDTO();
        }
        return null;
    }

    public LibrosDTO insertarLibro(LibrosDTO data){
        if(data == null || data.getTitulo().isEmpty()){
            throw new IllegalArgumentException("El titulo no puede ser null");
        }
        try {
            LibrosEntity entity = convertirAEntity(data);
            LibrosEntity libroGuardado = repo.save(entity);
            return convertirADTO(libroGuardado);
        }catch (Exception e){

            //Atrapamos y mostramos el error de que no se pudo registrar al usuario

            log.error("Error al registrar:" + e.getMessage());
            throw new ExceptionLibroNoRegistrado("Error al registrar");

        }
    }

    private LibrosEntity convertirAEntity(LibrosDTO data) {
        LibrosEntity entity = new LibrosEntity();
        entity.setTitulo(data.getTitulo());
        entity.setIsbn(data.getIsbn());
        entity.setAño_publicacion(data.getAño_publicacion());
        entity.setGenero(data.getGenero());
        entity.setId_autor(data.getId_autor());
        return entity;
    }


    private LibrosDTO convertirADTO(LibrosEntity librosEntity) {
        LibrosDTO dto = new LibrosDTO();
        dto.setId(librosEntity.getId());
        dto.setTitulo(librosEntity.getTitulo());
        dto.setIsbn(librosEntity.getIsbn());
        dto.setAño_publicacion(librosEntity.getAño_publicacion());
        dto.setGenero(librosEntity.getGenero());
        dto.setId_autor(librosEntity.getId_autor());
        return dto;
    }

    public LibrosDTO actualizarLibro (Long id, @Valid LibrosDTO json){
        LibrosEntity existente = repo.findById(id).orElseThrow();
        new ExceptionLibroNoEncontrado("Libro no encontrado");
        existente .setTitulo(json.getTitulo());
        existente.setIsbn(json.getIsbn());
        existente.setAño_publicacion(json.getAño_publicacion());
        existente.setGenero(json.getGenero());
        existente.setId_autor(json.getId_autor());
        LibrosEntity libroActualizado = repo.save(existente);
        return convertirADTO(libroActualizado);
    }
}




