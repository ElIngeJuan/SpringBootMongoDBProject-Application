package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Tipo de Habitaciones")
public class TipoHabitacion {
    
    @Id
    @Field(name ="_id")
    private Integer id;

    @Field(name ="tipo_habitacion")
    private String tipo_habitacion;

    @Field(name ="capacidad")
    private Integer capacidad;

    @Field(name ="dotacion")
    private String dotacion;

    @Field(name ="habitaciones")
    private List<Habitacion> habitaciones;

    public TipoHabitacion() {
    }

    public TipoHabitacion(Integer id, String tipo_habitacion, Integer capacidad, String dotacion,
            List<Habitacion> habitaciones) {
        this.id = id;
        this.tipo_habitacion = tipo_habitacion;
        this.capacidad = capacidad;
        this.dotacion = dotacion;
        this.habitaciones = habitaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getDotacion() {
        return dotacion;
    }

    public void setDotacion(String dotacion) {
        this.dotacion = dotacion;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

}