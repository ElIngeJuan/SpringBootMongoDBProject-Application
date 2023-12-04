package uniandes.edu.co.demo.modelo;

public class Habitacion {
    private Integer id;

    private Integer valor_noche;

    public Habitacion() {
    }

    public Habitacion(Integer id, Integer valor_noche) {
        this.id = id;
        this.valor_noche = valor_noche;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValor_noche() {
        return valor_noche;
    }

    public void setValor_noche(Integer valor_noche) {
        this.valor_noche = valor_noche;
    }


   
}
