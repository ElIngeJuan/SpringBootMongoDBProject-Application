package uniandes.edu.co.demo.modelo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Usuarios")
public class UsuarioPresencia {
    
    @Id
    private String id;

    private Integer documento;

    @Field(name = "reserva")
    private Integer reserva;

    private Integer check_in = 0;

    private Integer check_out = 0;

    public UsuarioPresencia() {
    }

    public UsuarioPresencia(String id, Integer documento, Integer reserva, Integer check_in, Integer check_out) {
        this.id = id;
        this.documento = documento;
        this.reserva = reserva;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Integer getReserva() {
        return reserva;
    }

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }

    public Integer getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Integer check_in) {
        this.check_in = check_in;
    }

    public Integer getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Integer check_out) {
        this.check_out = check_out;
    }




}
