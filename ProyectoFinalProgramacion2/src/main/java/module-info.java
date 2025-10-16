module co.uniquindio.edu.co.proyectofinalprogramacion2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.uniquindio.edu.co.proyectofinalprogramacion2 to javafx.fxml;
    exports co.uniquindio.edu.co.proyectofinalprogramacion2;
}