
package Clases;

public class Proveedores {
    private String Id, nombre, contacto, rfc;

    public Proveedores() {
    }

    public Proveedores(String Id, String nombre, String contacto, String rfc) {
        this.Id = Id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.rfc = rfc;
    }

    public Proveedores(String nombre, String contacto, String rfc) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.rfc = rfc;
    }

    public Proveedores(String Id, String nombre) {
        this.Id = Id;
        this.nombre = nombre;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
    
}
