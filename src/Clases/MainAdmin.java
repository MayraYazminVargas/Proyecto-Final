
package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainAdmin {
    private Connection cn;
    private Statement st;
    protected ResultSet resultset;
    protected String Query;
    
    /**
     * @param cn  Conexion con la base de datos
     */
    public MainAdmin(Connection cn){
        this.cn = cn;
        try { 
            st = cn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setConnection(Connection cn){
        this.cn = cn;
    }
    
    public Connection getConnection(){
        return cn;
    }
    
    protected void consulta(String query){
        try{                                               
            resultset = st.executeQuery(query);
        }catch(Exception e){}  
    }
    
    protected void modificar(String query){
        try{                                               
            st.executeQuery(query);
        }catch(Exception e){}  
    }               
    
    public String generarID(String op){
        String id, iden;
        String idn;        
        int a;
        iden = idn = "";
        switch(op){
            case "ventas_detalladas":
                iden="Id_Ven";
                id="V";
                break;
            case "compras_detalladas":
                iden="Id_Com";
                id="M";
                break;
            case "productos":                
                iden="Id_Prod";
                id="P";
                break;
            case "clientes":
                iden="Id_Ce";
                id="C";
                break;
            case "proveedores":
                iden="Id_Prov";
                id="Y";
                break;
            default: 
                id="not found";
        }
        try{                         
            Query="SELECT TOP 1 "+iden+" FROM "+op+" ORDER by "+iden+" DESC";
            consulta(Query);   
                                   
            while(resultset.next()){
                idn=resultset.getString(1);                
            }            
        }catch(Exception e){}                        
        if(idn.equals(""))
            idn="x0000";
        idn = idn.substring(1,idn.length());
        a= Integer.parseInt(idn);        
        a+=1;
        if(a<10){
            id=id+"000"+a;
        }else if(a<100){
            id=id+"00"+a;
        }else if(a<1000){
            id=id+"0"+a;
        }else
            id=id+a;
        return id;
    }
    
    public Productos[] consultarProductos(String orden){              
        Productos p[]; 
        switch(orden.toLowerCase()){            
            case "id":
                orden= "select * from Productos";
                break;
            case "nombre":
                orden="select * from productos ORDER by NomProd DESC";
                break;      
            case "existencia":
                orden="select * from productos ORDER by Existencia ASC";
                break;                                              
        }
        try{   
            int length=0;
            Query = "select count(1) from productos";
            consulta(Query);  
            while(resultset.next())
                length=resultset.getInt(1);            
            if(length>0){
            p =new Productos[length];
            
            consulta(orden);             
            int x =0;
            while(resultset.next()){
                p[x]= new Productos(resultset.getString(1),resultset.getString(2)
                        ,resultset.getFloat(3),resultset.getInt(4)
                        ,resultset.getInt(5));                
                x++;
            }
            return p; 
            }
        }catch(Exception e){
            System.err.println(e);
        }   
        return new Productos[0];                                   
    }
    
    public Clientes[] consultarClientes(String orden){              
        Clientes clientes[];        
        int x =0;
        boolean ban=true;
        switch(orden.toLowerCase()){            
            case "id":
                orden = "select * from clientes"; 
                break;
            case "nombre":
                orden="select * from clientes ORDER by Nom_Ce DESC";
                break;                       
            default:
                ban=false;                               
        }
        
        try{   
            int length=0;
            Query = "select count(1) from clientes";
            consulta(Query);  
            while(resultset.next())
                length=resultset.getInt(1);            
            clientes=new Clientes[length];                        
            consulta(orden);   
            if(ban){                
                while(resultset.next()){
                    clientes[x]= new Clientes(resultset.getString(1),resultset.getString(2)
                        ,resultset.getString(3),resultset.getString(4)
                        ,resultset.getString(5));              
                    x++;
                }
            }else{
                while(resultset.next()){
                    clientes[x]= new Clientes(resultset.getString(1)
                            ,resultset.getString(2));                
                    x++;
                }
            }       
            return clientes; 
        }catch(Exception e){
            System.err.println(e);
        }
        return new Clientes[0];                                   
    } 
    
    public Proveedores[] consultarProveedores(String orden){
        Proveedores proveedores[];   
        boolean ban=true;
        switch(orden.toLowerCase()){            
            case "id":
                orden = "select * from Proveedores";  
                break;
            case "nombre":
                orden="select * from Proveedores ORDER by Nom_Prov DESC";
                break;            
            default:
                ban=false;
        }
        
        try{   
            int length=0;
            Query = "select count(1) from Proveedores";
            consulta(Query);  
            while(resultset.next())
                length=resultset.getInt(1);            
            if(length>0){
                proveedores=new Proveedores[length];
            
                consulta(orden);             
                int x =0;
                if(ban){
                    while(resultset.next()){
                        proveedores[x]= new Proveedores(resultset.getString(1),resultset.getString(2)
                                ,resultset.getString(3),resultset.getString(4));              
                        x++;
                    }
                }
                else{
                    while(resultset.next()){
                        proveedores[x]= new Proveedores(resultset.getString(1)
                                ,resultset.getString(2));                
                        x++;
                    }
                }
                return proveedores; 
            }
        }catch(Exception e){
            System.err.println(e);
        }   
        return  new Proveedores[0];                                           
    }
}
