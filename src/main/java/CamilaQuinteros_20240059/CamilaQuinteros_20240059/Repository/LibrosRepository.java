package CamilaQuinteros_20240059.CamilaQuinteros_20240059.Repository;

import CamilaQuinteros_20240059.CamilaQuinteros_20240059.Entity.LibrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository <LibrosEntity, Long>  {
}
