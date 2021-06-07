
package Clases;

public class Productos {
    private String Id, nombre;
    private float precio;
    private int existencia, stock;

    public Productos() {
    }

    public Productos(String Id) {
        this.Id = Id;
    }

    
    public Productos(String Id, String nombre, float precio, int existencia, int stock) {
        this.Id = Id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
        this.stock = stock;
    }

    public Productos(String nombre, float precio, int existencia, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
        this.stock = stock;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
