package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.UsuarioPresencia;

public interface UsuarioPresenciaRepository extends MongoRepository<UsuarioPresencia, String> {

    @Query("{_id: ?0}")
    @Update("{$set:{'usuario.documento': ?1, 'usuario.tipo_documento': ?2, 'usuario.tipo_usuario': ?3, 'usuario.nombres': ?4, 'usuario.email': ?5}}")
    void editarUsuario(String id, Integer documento, String tipo_documento, String tipo_usuario, String nombres, String email);
    

    @Query("{'usuario.documento': ?0}")
    @Update("{$push:{reserva:{?1}}}")
    void aniadirReservaUsuario(Integer Cedula, Integer idreserva);

    @Query("{'_id': ?0}")
    UsuarioPresencia findByUsuario(Integer _id);
}
