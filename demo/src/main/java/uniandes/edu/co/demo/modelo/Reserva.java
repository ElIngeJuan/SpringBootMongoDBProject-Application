package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Reservas")
public class Reserva {
    
    @Id
    @Field(name ="_id")
    private Integer id;

    @Field(name ="usuario_presencia")
    private Integer usuario_presencia;

    @Field(name ="habitacion")
    private Integer habitacion;


    @Field(name ="fecha_entrada")
    private String fecha_entrada;

    @Field(name ="fecha_salida")
    private String fecha_salida;

    @Field(name ="num_noches")
    private Integer num_noches;

    @Field(name ="num_personas")
    private Integer num_personas;

    @Field(name = "consumos")
    private List<Consumo> consumos;

    public Reserva() {
    }

    public Reserva(Integer id, Integer usuario_presencia, Integer habitacion, String fecha_entrada, String fecha_salida,
            Integer num_noches, Integer num_personas, List<Consumo> consumos) {
        this.id = id;
        this.usuario_presencia = usuario_presencia;
        this.habitacion = habitacion;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.num_noches = num_noches;
        this.num_personas = num_personas;
        this.consumos = consumos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario_presencia() {
        return usuario_presencia;
    }

    public void setUsuario_presencia(Integer usuario_presencia) {
        this.usuario_presencia = usuario_presencia;
    }

    public Integer getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Integer habitacion) {
        this.habitacion = habitacion;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Integer getNum_noches() {
        return num_noches;
    }

    public void setNum_noches(Integer num_noches) {
        this.num_noches = num_noches;
    }

    public Integer getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(Integer num_personas) {
        this.num_personas = num_personas;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }



}
