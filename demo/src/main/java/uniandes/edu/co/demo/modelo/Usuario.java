package uniandes.edu.co.demo.modelo;

public class Usuario {
    
    private Integer documento;
    private String tipo_documento;
    private String tipo_usuario;
    private String nombres;
    private String email;

    public Usuario() {
    }

    public Usuario(Integer documento, String tipo_documento, String tipo_usuario, String nombres, String email) {
        this.documento = documento;
        this.tipo_documento = tipo_documento;
        this.tipo_usuario = tipo_usuario;
        this.nombres = nombres;
        this.email = email;
    }
    public Integer getDocumento() {
        return documento;
    }
    public void setDocumento(Integer documento) {
        this.documento = documento;
    }
    public String getTipo_documento() {
        return tipo_documento;
    }
    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }
    public String getTipo_usuario() {
        return tipo_usuario;
    }
    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
