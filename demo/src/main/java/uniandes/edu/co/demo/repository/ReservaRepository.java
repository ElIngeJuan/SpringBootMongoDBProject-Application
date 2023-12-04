package uniandes.edu.co.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Reserva;

public interface ReservaRepository extends MongoRepository<Reserva, Integer> {


    public class RespuestaGrupo {
        private Integer habitacion;
        private Double indice_ocupacion;

        public RespuestaGrupo() {
        }

        public RespuestaGrupo(Integer habitacion, Double indice_ocupacion) {
            this.habitacion = habitacion;
            this.indice_ocupacion = indice_ocupacion;
        }

        public Integer getHabitacion() {
            return habitacion;
        }

        public void setHabitacion(Integer habitacion) {
            this.habitacion = habitacion;
        }

        public Double getIndice_ocupacion() {
            return indice_ocupacion;
        }

        public void setIndice_ocupacion(Double indice_ocupacion) {
            this.indice_ocupacion = indice_ocupacion;
        }
        
    }


    public class RespuestaGrupoR2 {
        private Integer habitacion;
        private Integer totalRecaudado;

        public RespuestaGrupoR2() {
        }

        public RespuestaGrupoR2(Integer habitacion, Integer totalRecaudado) {
            this.habitacion = habitacion;
            this.totalRecaudado = totalRecaudado;
        }

        public Integer getHabitacion() {
            return habitacion;
        }

        public void setHabitacion(Integer habitacion) {
            this.habitacion = habitacion;
        }

        public Integer getTotalRecaudado() {
            return totalRecaudado;
        }

        public void setTotalRecaudado(Integer totalRecaudado) {
            this.totalRecaudado = totalRecaudado;
        }
        
    }

    public class consumo {
        private String servicio;
        private String fecha;
        private Integer valor;

        public consumo() {
        }

        public consumo(String servicio, String fecha, Integer valor) {
            this.servicio = servicio;
            this.fecha = fecha;
            this.valor = valor;
        }

        public String getServicio() {
            return servicio;
        }

        public void setServicio(String servicio) {
            this.servicio = servicio;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public Integer getValor() {
            return valor;
        }

        public void setValor(Integer valor) {
            this.valor = valor;
        }
        
    }

    public class RespuestaGrupoR3 {
        private consumo consumos;

        public RespuestaGrupoR3() {
        }

        public RespuestaGrupoR3(consumo consumos) {
            this.consumos = consumos;
        }

        public consumo getConsumos() {
            return consumos;
        }

        public void setConsumos(consumo consumos) {
            this.consumos = consumos;
        }

        
    }

    public class info {
        private Integer documento;
        private String agrupado;
        public info(Integer documento, String agrupado) {
            this.documento = documento;
            this.agrupado = agrupado;
        }
        public Integer getDocumento() {
            return documento;
        }
        public void setDocumento(Integer documento) {
            this.documento = documento;
        }
        public String getAgrupado() {
            return agrupado;
        }
        public void setAgrupado(String agrupado) {
            this.agrupado = agrupado;
        }
        
    }


    public class RespuestaGrupoR4 {
        private info _id;
        private Integer vecesUtilizado;
        private String ultimaFecha;
        public RespuestaGrupoR4(info _id, Integer vecesUtilizado, String ultimaFecha) {
            this._id = _id;
            this.vecesUtilizado = vecesUtilizado;
            this.ultimaFecha = ultimaFecha;
        }
        public info get_id() {
            return _id;
        }
        public void set_id(info _id) {
            this._id = _id;
        }
        public Integer getVecesUtilizado() {
            return vecesUtilizado;
        }
        public void setVecesUtilizado(Integer vecesUtilizado) {
            this.vecesUtilizado = vecesUtilizado;
        }
        public String getUltimaFecha() {
            return ultimaFecha;
        }
        public void setUltimaFecha(String ultimaFecha) {
            this.ultimaFecha = ultimaFecha;
        }


    
        
    }


    @Query(value = "{'usuario_presencia': ?0}")
    List<Reserva> buscarDocumentos(Integer cedula);

    @Query(value = "{'_id': ?0, 'consumos.$.servicio': ?1, 'consumos.$.fecha': ?2, 'consumos.$.valor': ?3}")
    Reserva buscarTipoHabitacionPorIdYHabitacionId(Integer id, String servicio, String fecha, Integer valor);

    @Query("{_id: ?0, 'consumos.servicio': ?1, 'consumos.fecha': ?2, 'consumos.valor': ?3}")
    @Update("{$set: { 'consumos.$.servicio': ?4, 'consumos.$.fecha': ?5, 'consumos.$.valor': ?6 }}")
    void editarValorNocheDeHabitacionEnTipoHabitacion(Integer id, String servicio, String fecha, Integer valor, String servicioNuevo, String fechaNueva, Integer valorNuevo);
    
    @Query("{_id: ?0, 'consumos.servicio': ?1, 'consumos.fecha': ?2, 'consumos.valor': ?3}")
    @Update("{$pull:{consumos:{servicio:?1, fecha:?2, valor:?3}}}")
    void eliminarHabitacionDeTipoHabitacion(Integer id, String servicio, String fecha, Integer valor);

    @Query("{_id: ?0}")
    @Update("{$push:{consumos:{servicio:?1, fecha:?2, valor:?3}}}")
    void aniadirHabitacionATipoHabitacion(Integer id, String servicio, String fecha, Integer valor);

    @Query(value = "{}", sort = "{_id: -1}", fields = "{_id:1}")
    Collection<Reserva> buscarId();
    
    @Aggregation(pipeline = {
        "{$match: { 'fecha_entrada': { $gte: '2023-01-01', $lte: '2023-12-31' } }}",
        "{$group: { _id: '$habitacion', total_noches: { $sum: '$num_noches' } }}",
        "{$project: { _id: 0, habitacion: '$_id', indice_ocupacion: { $round: [{ $multiply: [{ $divide: ['$total_noches', 365] }, 100] }, 2] } }}",
        "{$sort: { indice_ocupacion: -1 }}"
    })
    List<RespuestaGrupo> darIndiceOcupacion();
    
    @Aggregation(pipeline = {
        "{$match: { 'fecha_entrada': { $gte: '2023-01-01', $lte: '2023-12-31' } }}",
        "{$unwind: '$consumos'}",
        "{$group: { _id: { habitacion: '$habitacion' }, totalRecaudado: { $sum: '$consumos.valor' } }}",
        "{$project: { _id: 0, habitacion: '$_id.habitacion', totalRecaudado: 1 }}"
    })
    List<RespuestaGrupoR2> calcularRecaudacionPorHabitacion();
    

    @Aggregation(pipeline = {
        "{$unwind: '$consumos'}",
        "{$match: { 'usuario_presencia': ?0, 'consumos.fecha': { $gte: ?1, $lte: ?2 } }}",
        "{$project: { _id: 0, 'consumos.servicio': 1, 'consumos.fecha': 1, 'consumos.valor': 1 }}"
    })
    List<RespuestaGrupoR3> obtenerConsumosPorUsuarioYFecha( Integer cedula, String fechaInicio, String fechaFin);                       
    
    @Aggregation(pipeline = {
        "{$match: { 'fecha_entrada': { $gte: ?0, $lte: ?1 } }}",
        "{$unwind: '$consumos'}",
        "{$group: { _id: { documento: '$usuario_presencia', agrupado: ?2 }, vecesUtilizado: { $sum: 1 }, ultimaFecha: { $last: '$consumos.fecha' } }}",
        "{$sort: { ?3: ?4 }}"
    })
    List<RespuestaGrupoR4> obtenerConsumosPGrupoR4s(String fechaInicio, String fechaFin, String agruparPor, String criterioOrden, Integer tipoOrden);
    
}
