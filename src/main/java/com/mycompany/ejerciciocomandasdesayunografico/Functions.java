package com.mycompany.ejerciciocomandasdesayunografico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author Pablo Martínez y Santiago Ucero. Cesur 2ªDAM, Diseño de Interfaces
 */
public class Functions {
    
    double precioTotal;
    double precioPedido;
    
    public void resetPrecioPedido() {
        this.precioPedido = 0;
    }

    public double getPrecioPedido() {
        precioTotal += precioPedido;
        return precioPedido;
    }
        
    public double getPrecioTotal(){
        return precioTotal;
    }
        
    public void setBebida(String bebida){
        
        switch(bebida){
            case "Café Solo - 0.8€": this.precioPedido+=0.8; break;
            case "Café con Leche - 1€": this.precioPedido+=1; break;
            case "Refresco - 1.5€": this.precioPedido+=1.5; break;
            case "Colacao - 1.5€": this.precioPedido+=1.5; break;
            default: break;
        }
    }
    
    public void setComida(String comida){
        
        switch(comida){
            case "Pitufo Mixto - 1.5€": this.precioPedido+=1.5; break;
            case "Pitufo Bacon - 1.7€": this.precioPedido+=1.7; break;
            case "Pitufo Lomo - 2€": this.precioPedido+=2; break;
            case "Bocata Paté - 2.5€": this.precioPedido+=2.5; break;
            case "Bocata Bacon - 3€": this.precioPedido+=3; break;
            case "Bocata Chorizo - 3.5€": this.precioPedido+=3.5; break;
            default: break;
        }
    }
    
    // --- A partir de aquí, métodos para conexión con bases de datos ---
    String url;
    String user;
    String password;
    
    public void conexionBD() throws FileNotFoundException, IOException{
        Properties params = new Properties();
            params.load(new FileReader("bbdd.cfg"));
            String protocol = params.getProperty("protocol");
            String server = params.getProperty("server");
            String port = params.getProperty("port");
            String database = params.getProperty("database");
            String extra = params.getProperty("extra");
            user = params.getProperty("user");
            password = params.getProperty("password");
            url = protocol + server + ":" + port + "/" + database + "?" + extra;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
