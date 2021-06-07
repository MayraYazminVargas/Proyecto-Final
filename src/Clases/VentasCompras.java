
package Clases;

public class VentasCompras {
    private String id; //compra venta
    private String idP; //Producto
    private int cantidad;
    private float subtotal;
    private int cont;
    public VentasCompras(String id, String idP, int cantidad, float subtotal){
        this.id=id;
        this.idP=idP;
        this.cantidad=cantidad;
        this.subtotal=subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }                   

}
