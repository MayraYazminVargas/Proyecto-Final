
package Clases;

import java.sql.Connection;

public class AdministrarClientes extends MainAdmin{
    
    public AdministrarClientes(Connection cn){
        super(cn);
    }
    public String nombre = "";
    public Clientes buscarClientes(String id){
        String  idd = "", domicilio="" , correo="";
        String telefono="";

        try{
            Query = "select * from clientes where Id_Ce = '"+id+"'";
            consulta(Query);
             while(resultset.next()){
                idd = resultset.getString(1);
                nombre = resultset.getString(2);
                telefono = resultset.getString(3);
                correo = resultset.getString(4);
                domicilio =resultset.getString(5);
             }
        }catch(Exception e){}
        if(id.equals(idd)){ //Ver si existe   
            return new Clientes(nombre, telefono, correo, domicilio);
        }else
            return null; //Si no hay coincidencia mandamos un nulo
    }
    
    public void modificarClientes(Clientes cliente){
        String  idd = "", telefono="", correo ="", domicilio="";
   
        idd = cliente.getId();
        nombre = cliente.getNombre();
        telefono = cliente.getTelefono();
        correo = cliente.getCorreo();
        domicilio = cliente.getDireccion();
        Query = "update clientes set Nom_Ce = '"+nombre+"', Telefono = '"+telefono+"', Correo = '"+correo+"', Direccion ='"+domicilio +"' where Id_Ce = '"+idd+"'";
        try{
            modificar(Query);
        }catch(Exception e){}
    }
    public void eliminarCliente(String id){
       Query = "delete from clientes where Id_Ce = '"+id+"'";
       try{
            consulta(Query);
        }catch(Exception e){}
            
    }
    public void añadirCliente(Clientes cliente){
        String  idd = "", telefono = "", correo ="", domicilio = "";

        idd = cliente.getId();
        nombre = cliente.getNombre();
        telefono = cliente.getTelefono();
        correo = cliente.getCorreo();
        domicilio = cliente.getDireccion();
        Query = "insert into Clientes values('"+idd+"','"+nombre+"','"+telefono+"','"+correo+"','"+domicilio+"')";
        try{
            consulta(Query);
        }catch(Exception e){}
    }
}
