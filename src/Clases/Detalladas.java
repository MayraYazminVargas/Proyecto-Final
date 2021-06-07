
package Clases;


public class Detalladas {
    String id, idAquel;
    float total;
    int dia, mes,anio;
    
    Detalladas() {                
    }
    
    Detalladas(String id, String idAquel, int dia, int mes, int anio, float total) {
        this.id = id;
        this.idAquel=idAquel;
        this.total=total;
        this.dia=dia;
        this.mes=mes;
        this.anio=anio;  
    }

    public void setCabeza(String id, String idAquel,int dia, int mes, int anio, float total) { //falta fecha
        this.id = id;
        this.idAquel=idAquel;
        this.total=total;
        this.dia=dia;
        this.mes=mes;
        this.anio=anio;        
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    public String getId() {
        return id;
    }  

    public String getIdAquel() {
        return idAquel;
    }

    public void setIdAquel(String idAquel) {
        this.idAquel = idAquel;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
