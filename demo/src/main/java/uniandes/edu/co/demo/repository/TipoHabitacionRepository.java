package uniandes.edu.co.demo.repository;


import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Habitacion;
import uniandes.edu.co.demo.modelo.Reserva;
import uniandes.edu.co.demo.modelo.TipoHabitacion;




public interface TipoHabitacionRepository extends MongoRepository<TipoHabitacion, Integer> {

    @Query(value = "{_id: ?0, 'habitaciones': {$elemMatch: {id: ?1}}}")
    Habitacion buscarTipoHabitacionPorIdYHabitacionId(Integer id_bar, Integer idHabitacion);

    @Query("{_id: ?0}")
    @Update("{$push:{habitaciones:{id:?1, valor_noche:?2}}}")
    void aniadirHabitacionATipoHabitacion(Integer id_bar, Integer id, Integer valor_noche);
    
    @Query("{_id: ?0}")
    @Update("{$pull:{habitaciones:{id:?1}}}")
    void eliminarHabitacionDeTipoHabitacion(Integer id_bar, Integer id);

    @Query("{_id: ?0, 'habitaciones.id': ?1}")
    @Update("{$set:{'habitaciones.$.valor_noche': ?2}}")
    void editarValorNocheDeHabitacionEnTipoHabitacion(Integer id_bar, Integer id, Integer nuevoValor);

    @Query(value = "{}", sort = "{_id: -1}", fields = "{_id:1}")
    Collection<TipoHabitacion> buscarId();
}
