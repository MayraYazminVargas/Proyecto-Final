/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mainsito;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marti
 */
public class Conexion {
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url ;
    String user = "usuarioCC";
    String con ="MyPassword";
    private Connection cn;
    public Conexion(String servidor, String puerto)  {   
        int n;
        File archivo=new File("url.txt");        
        FileWriter fw;
        try {
            fw = new FileWriter(archivo);
            BufferedWriter bw=new BufferedWriter(fw);
            url="jdbc:sqlserver://"+servidor+":"+puerto+";databaseName=FruteriaDB";        
            bw.write(url);
            bw.flush();
        } catch (IOException ex) {
            System.err.print("no se pudo"+ex);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    public Conexion() throws FileNotFoundException, IOException  {            
        FileReader fr;
        fr = new FileReader("url.txt");
            BufferedReader br=new BufferedReader(fr);
            url= br.readLine();  
    }    
    
    public Connection getConnection(){
        return cn;
    }
    //Conecction
    public void conectar() throws SQLException{
        cn =null;
        try 
	{             
            Class.forName(driver); 
            cn=DriverManager.getConnection(url,user,con);
	} 
	catch(java.lang.ClassNotFoundException e) 
	{ 
            System.err.println("Problemas al cargar el driver "); 
            System.err.println(e.getMessage()); 
	}
        //return cn;
    }
    
    public void desconectar(){
        try {
            if(cn!=null)
                cn.close();		
        } 
	catch (SQLException e) {
		System.err.print("Error: "+e.getMessage());
	}
    }
    
}
