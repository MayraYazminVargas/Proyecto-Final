
package Clases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AdministrarProductos extends MainAdmin{    
    public AdministrarProductos(Connection cn){
        super(cn);
    }

    public String nombre = "";
    
   
    public void agregarProducto(Productos producto) {
        String Id;
        float precio;
        int existencia;
        int stock;
        Id = producto.getId();
        nombre = producto.getNombre();
        precio = producto.getPrecio();
        existencia = producto.getExistencia();
        stock = producto.getStock();
        try{                        
            Query ="insert into productos values('"+Id+"','"+nombre
                    +"' ,"+precio+","+existencia+","+stock+")";
            consulta(Query);            
        }catch(Exception e){}  
        
    }
  
    public Productos buscarProducto(String id){
        String  idd = "";
        float precio = 0;
        int existencia = 0;
        int stock=0;
        try{
            Query = "select * from productos where Id_Prod ='"+id+"'";
            //a veces imprimimos el query para ver si se escribio bien
            //System.out.println(Query);
            consulta(Query);
            //la variable resulset se actualiza al mandar un metodo consulta(Query)
            while(resultset.next()){
                //el while es necesari opara leer datos de la base de datos
                idd = resultset.getString(1);
                nombre = resultset.getString(2);            
                precio = resultset.getFloat(3);
                existencia = resultset.getInt(4);
                stock = resultset.getInt(5);
            }
        }catch(Exception e){}
        if(idd.equals(id)){
           Productos pr = new Productos(nombre, precio, existencia, stock );
           return pr;
       }else
           return null;
   }      
   
   public void modificarProducto(Productos p){
        String  idd = "";
        float precio = 0;
        int existencia = 0;
        int stock;
        idd = p.getId();
        nombre = p.getNombre();
        precio = p.getPrecio();
        existencia = p.getExistencia();
        stock = p.getStock();
        Query = "update productos set NomProd = '"+nombre+"', Precio = '"+precio+"', Existencia = '"+existencia
                +"', Stock ='"+stock+"' where Id_Prod = '"+idd+"'";
        try{
            modificar(Query);
        }catch(Exception e){}
   }
   /**
     * 
     * @param idP
     * @param cant 
     */
    public void actualizarUnidades(String idP, int cant){        
        Query = "update productos set existencia = "+cant+" where Id_Prod = '"+idP+"'";
        try{
            modificar(Query);
        }catch(Exception e){}
    }    
    
   public void eliminarProdcuto(String id){
       try{                        
            Query = "delete from productos where Id_Prod = '"+id+"'"; 
            consulta(Query);            
        }catch(Exception e){}  
                        
   }

}
