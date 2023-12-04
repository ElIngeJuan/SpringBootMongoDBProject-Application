package uniandes.edu.co.demo.modelo;



public class Consumo {

    private String servicio;
    private String fecha;
    private Integer valor;

    public Consumo() {
    }

    public Consumo(String servicio, String fecha, Integer valor) {
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