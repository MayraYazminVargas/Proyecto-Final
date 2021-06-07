
package Clases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AdministrarProveedores extends MainAdmin{
    
    public AdministrarProveedores(Connection cn){
        super(cn);
    }
    public void agregarProveedor(Proveedores p){
        String id = "", nombre = "", contacto = "", rfc="";
        id = p.getId();
        nombre = p.getNombre();
        contacto = p.getContacto();
        rfc = p.getRfc();
        

        try{
            Query = "insert into proveedores values('"+id+"','"+nombre+"','"+contacto+"','"+rfc+"')";
            consulta(Query);
        }catch(Exception e){}
    }
    
    public Proveedores buscarProveedor(String ID){
        String id = "", nombre = "", contacto = "", rfc = "";
    String nl = "";
        try{
            Query = "select * from proveedores where Id_Prov = '"+ID+"'";
            consulta(Query);
            while(resultset.next()){
                id =  resultset.getString(1);
                nombre =  resultset.getString(2);
                contacto = resultset.getString(3);
                rfc = resultset.getString(4);
            }
        }catch(Exception e){
        }
        if(ID.equals(id)){ 
            return new Proveedores(nombre,contacto,rfc);
        }else
            return null;
    }
    
    public void eliminarProveedor(String idd){
        Query = "delete from proveedores where Id_Prov = '"+idd+"'";
        try{
            consulta(Query);
        }catch(Exception e){} 
    }
    
    public void modificarProveedor(Proveedores p){
        String id = "", nombre = "", contacto = "", rfc="";
        id = p.getId();
        nombre = p.getNombre();
        contacto = p.getContacto();
        rfc = p.getRfc();
        Query = "update proveedores set Nom_Prov = '"+nombre+"', Contacto = '"+contacto+"', RFC = '"+rfc+"' where Id_Prov = '"+id+"'";
         try{
            modificar(Query);
        }catch(Exception e){}
    }
}
