package com.mycompany.ejerciciocomandasdesayunografico;

import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author Pablo Martínez y Santiago Ucero. Cesur 2ªDAM, Diseño de Interfaces
 */
public class SecondaryController {
    
    @FXML
    private Label pedidosTotales;
    @FXML
    private Label ingresosTotales;
    @FXML
    private Label precioMedio;
    
    public void setData(int numeroPedidos, double precioTotal){
        
        // Para que sólo aparezcan 2 decimales
        DecimalFormat df = new DecimalFormat("#.##");
        
        double media = precioTotal / ((double) numeroPedidos);
        
        pedidosTotales.setText(""+numeroPedidos);
        ingresosTotales.setText(df.format(precioTotal)+"€");
        precioMedio.setText(df.format(media)+"€");
    }
    
    
}