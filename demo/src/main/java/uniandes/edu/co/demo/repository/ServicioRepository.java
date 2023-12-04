package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.demo.modelo.Servicio;

@Repository
public interface ServicioRepository extends MongoRepository<Servicio, String> {

    @Query("{ 'tipo' : ?0 }")
    List<Servicio> findByTipo(String tipo);

    @Query("{'_id':?0 }")
    @Update("{$push:{maquinas:{?1}}}")
    void agregarMaquinaDeGym(String id, String maquina);

}
