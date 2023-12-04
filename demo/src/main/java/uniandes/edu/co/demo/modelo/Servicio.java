package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Servicios")
public class Servicio {

    @Id
    private String id;

    private String nombre;

    private String tipo;

    private Integer capacidad;

    private String estiloMusica;

    private Integer profundidad;

    private List<String> maquinas;

    private String estiloRestaurante;

    private List<String> listaProductos;

    private String tipoTienda;

    private String duracion;

    private Integer numeroPrendas;

    private String utensilio;

    private String equipos;

    private String horarioApertura;

    private String horarioCierre;

    public Servicio() {
    }


    public Servicio(String id, String nombre, String tipo, Integer capacidad, String estiloMusica, Integer profundidad,
            List<String> maquinas, String estiloRestaurante, List<String> listaProductos, String tipoTienda,
            String duracion, Integer numeroPrendas, String utensilio, String equipos, String horarioApertura,
            String horarioCierre) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estiloMusica = estiloMusica;
        this.profundidad = profundidad;
        this.maquinas = maquinas;
        this.estiloRestaurante = estiloRestaurante;
        this.listaProductos = listaProductos;
        this.tipoTienda = tipoTienda;
        this.duracion = duracion;
        this.numeroPrendas = numeroPrendas;
        this.utensilio = utensilio;
        this.equipos = equipos;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstiloMusica() {
        return estiloMusica;
    }

    public void setEstiloMusica(String estiloMusica) {
        this.estiloMusica = estiloMusica;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public List<String> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<String> maquinas) {
        this.maquinas = maquinas;
    }

    public String getEstiloRestaurante() {
        return estiloRestaurante;
    }

    public void setEstiloRestaurante(String estiloRestaurante) {
        this.estiloRestaurante = estiloRestaurante;
    }

    public List<String> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<String> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getTipoTienda() {
        return tipoTienda;
    }

    public void setTipoTienda(String tipoTienda) {
        this.tipoTienda = tipoTienda;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Integer getNumeroPrendas() {
        return numeroPrendas;
    }

    public void setNumeroPrendas(Integer numeroPrendas) {
        this.numeroPrendas = numeroPrendas;
    }

    public String getUtensilio() {
        return utensilio;
    }

    public void setUtensilio(String utensilio) {
        this.utensilio = utensilio;
    }

    public String getEquipos() {
        return equipos;
    }

    public void setEquipos(String equipos) {
        this.equipos = equipos;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }



}
