module com.mycompany.ejerciciocomandasdesayunografico {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.ejerciciocomandasdesayunografico to javafx.fxml;
    exports com.mycompany.ejerciciocomandasdesayunografico;
    
    opens modelos to javafx.fxml;
    exports modelos;
}
