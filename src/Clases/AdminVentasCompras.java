package Clases;

import java.sql.Connection;

/**
 *  Clase permite interactuar con los datos de <br>
 * .
*/
public class AdminVentasCompras extends MainAdmin {
    /**
      VentasCompras cv[] es donde se almacena la informacion de la tabla Compra<br>
      o Venta de una determinada detalladas.
     */
    private VentasCompras cv[];    
    private Detalladas detalladas;
    /**
     * Esta variable especifica si se trata de una compra o de una venta
     */
    private String op; 
    private int numproductos;
    private String iden;
    
    /**
     * @param cn Conexion con la base de datos
     */
    public AdminVentasCompras(Connection cn){
        super(cn);
        numproductos=0;
        cv = new VentasCompras[20];
        detalladas= new Detalladas("0","",0,0,0,0.0f);
    } 
    /**
     * 
     * @return  opcion "compra" o "venta"
     */
    public String getOp(){
        return op;
    }
    /**
     * Con este metodo set cambiamos si nos referimos a tabla ventas o tabla compras
     * @param op "ventas" o "compras"
     */
    public void setOp(String op) {    
        this.op = op;
        cv = new VentasCompras[20];
        switch(op){
            case "ventas":
                iden="Id_Ven";
                break;
            case "compras":
                iden="Id_Com";
                break;
            default:
                System.err.print("op No valida en Metodo setOp() en clase Clases.AdminVentasCompras");
        }        
    }
    
    /**
     * Este metodo se usa para guardar las detelladas de una nuevo compra o venta
     * @param id
     * @param idAquel
     * @param total
     * @param dia
     * @param mes
     * @param anio 
     */
    public void setDetalladas(String id, String idAquel, float total, int dia, int mes, int anio) {

        detalladas.setCabeza(id,idAquel, dia, mes, anio, total);        
    }
    
    /**
     * 
     * @param x Numero de productos
     */
    public void setNumproductos(int numproductos){
        this.numproductos=numproductos;
    }
    
    /**
     * 
     * @return Un entero con el numero de elementos agregados a cv[]
     */
    public int getNumproductos(){        
        //se actualiza al usar get o set de compra venta
        return numproductos;
    }
    
    
    /**
     * Agrega datos a la variable cv[] 
     * 
     * @param id ID de la compra o venta
     * @param idP ID del producto
     * @param cantidad Cantidad de productos
     * @param subtotal Cantidad por el precio
     */
    public void agregarCompraVenta(String id, String idP, int cantidad,float subtotal){
        cv[numproductos]=new VentasCompras(id,idP,cantidad,subtotal);
        numproductos++;
    }
    
    public void agregarCompraVenta(int index, String id, String idP, int cantidad,float subtotal){
        cv[index]=new VentasCompras(id,idP,cantidad,subtotal);
    }
    
    /**
     * Quita datos de la variable cv[] 
     * @param index 
     */
    public void quitarCompraVenta(int index){
        for (int x = index; x < numproductos-1; x++) //confirmar el -1
            cv[x] = cv [x+1];
        numproductos--;
    }
    
    public int buscarCompraVenta(String idP){
        for (int x = 0; x < numproductos; x++) 
            if(cv[x].getIdP().equals(idP))
                return x;
        return -1;
    }
    /**
     * regresa la cantidad que posee un determinado elemento del objeto  <br>
     * CompraVenta cv dado el indice dado.
     * @param index
     * @return 
     */
    public int getCantidadCV(int index){
        return cv[index].getCantidad();        
    }
    /**
     * actualiza las cantidades del objeto CompraVenta cv.
     * @param index
     * @param cant 
     */
    public void setCantidadCV(int index, int cant){
        cv[index].setCantidad(cant);        
    }         
    
    /**
     * Guarda en la base de datos los elementos almacenados hasta el momento<br>
     * en las variables de clase detalladas y cv[]
     */       
    public void guardar(){            
        //aqui guardamos el contenido de encabezado y cv[] en la base de datos;   
        try{     
            Query ="insert into "+op+"_detalladas values('"+detalladas.getId()+"','"+detalladas.getIdAquel()
                    +"','"+detalladas.getDia()+"' ,"+detalladas.getMes()
                    +","+detalladas.getAnio()+","+detalladas.getTotal()+")";
            consulta(Query);
            for(int x=0; x<numproductos;x++){
                Query=("insert into "+op+" values('"+detalladas.getId()+"','"+cv[x].getIdP()+"',"
                    +cv[x].getCantidad()+" ,"+cv[x].getSubtotal())+")";       
                consulta(Query);
            }                    
        }catch(Exception e){
            System.err.println(e);
        }   
        
        numproductos=0;
    }
    
    /**
     * Permite eliminar una fila de la tabla dado su id
     * @param id Cadena con el id del elemento a eliminar
     */ 
    public void eliminar(String id){
        // primera fase regresar las existencias 
        cv=consultarCompraVenta(id);
        int[] cant=new int[cv.length];
        for(int x=0; x<cant.length;x++){
            cant[x]=-cv[x].getCantidad();
        }
        actualizarUnidades(cant);
        
        // segunda fase quitar los datos
        Query="DELETE FROM "+op+" WHERE "+iden+"='"+id+"' "; 
        try{                 
            consulta(Query);
            Query="DELETE FROM "+op+"_detalladas WHERE "+iden+"='"+id+"' ";
            consulta(Query);
        }catch(Exception e){
            System.err.println(e);
        } 
        
    }
    
    /**
     * 
     * @param orden Cadena con el orden en que se desea organizar la tabla
     * @return 
     */
    public Detalladas[] consultarDetalladas(String orden){
        //busca y envia la tabla encabezado                
        Detalladas c[];    
        switch(orden.toLowerCase()){            
            case "fecha":
                char a=iden.charAt(2);   
                orden="select * from "+op+"_detalladas ORDER by yearV"+a
                        +" ASC, monthV"+a+" ASC, dayV"+a+" ASC";                
                break;
            case "idaquel":
                if(op.equals("ventas"))
                    orden="select * from ventas_detalladas ORDER by Id_Ce ASC";
                else
                    orden="select * from compras_detalladas ORDER by Id_Prov ASC";
                break;
            default:
                orden = "select * from "+op+"_detalladas";                
        }
        try{   
            int length=0;
            Query = "select count(1) from "+op+"_detalladas";
            consulta(Query);  
            while(resultset.next())
                length=resultset.getInt(1);            
            
            c=new Detalladas[length];
            
            Query = orden;
            consulta(Query);             
            int x =0;
            while(resultset.next()){
                c[x]= new Detalladas(resultset.getString(1),resultset.getString(2)
                        ,resultset.getInt(3),resultset.getInt(4)
                        ,resultset.getInt(5),resultset.getFloat(6));                
                x++;
            }
            return c; 
        }catch(Exception e){
            System.err.println(e);
        }   
        return new Detalladas[0];                                   
    }   
    
    /**
     * Este metodo se usa para obtener los datos de las detalladas de una <br>
     * compra venta existente.
     * 
     * @param id 
     * @return  Un Objeto detallado
     */
    public Detalladas consultarDetalladaas(String id){                                     
        try{                                             
            Query="select * from "+op+"_detalladas where "+iden+"='"+id+"'";            
            consulta(Query);             

            while(resultset.next()){
                if(id.equals(resultset.getString(1))){
                    detalladas.setCabeza(id,resultset.getString(2)
                            ,resultset.getInt(3),resultset.getInt(4)
                            ,resultset.getInt(5),resultset.getFloat(6));                
                    //x++;
                }
            }
        }catch(Exception e){
            System.err.println("Error en detalladas: "+e);
        }                                     
            
        return detalladas;
    }
    
    /**
     * 
     * @param idCV Cadena con el id a buscar en la tabla Compra o Venta
     * @return 
     */
    public VentasCompras[] consultarCompraVenta(String idCV){
        //busca y envia la tabla encabezado                
        VentasCompras c[];        
        
        try{   
            //int length=0;
            
            Query = "select count(1) from "+op+" where "+iden+"='"+idCV+"'";            
            consulta(Query);  
            while(resultset.next())
                numproductos=resultset.getInt(1);                        
            c=new VentasCompras[numproductos];
            
            Query = "select * from "+op+" where "+iden+"='"+idCV+"'";
            consulta(Query);             
            int x =0;
            while(resultset.next()){
                c[x]= new VentasCompras(resultset.getString(1),resultset.getString(2)
                        ,resultset.getInt(3),resultset.getInt(4));                
                x++;
            }
            return c; 
        }catch(Exception e){
            System.err.println("Error en compraventa "+e);
        }   
        return c= null;                                   
    }
    
    /**
     * Esta clase actualiza las unidades de los productos en la base de datos <br>
     * de acuerdo con la cadena que es enviada como argumento. <br>
     * Si op esta como ventas se actualizara restando, si esta como compras  <br>
     * actualizara sumando.
     * @param idP
     * @param cant 
     */
    public void actualizarUnidades(int[] cant){        
        
        try{
            for(int x=0;x<numproductos;x++){                
                switch(op){
                    case "ventas":
                        Query = "update productos set Existencia -= "+cant[x]
                                +" where Id_Prod = '"+cv[x].getIdP()+"'";
                        break;
                    case "compras":
                        Query = "update productos set Existencia += "+cant[x]
                                +" where Id_Prod = '"+cv[x].getIdP()+"'";                        
                        break;
                }                
                modificar(Query);
            }
        }catch(Exception e){}
    }    
         
}
