module co.uniquindio.edu.co.pfp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.uniquindio.edu.co.pfp2 to javafx.fxml;
    exports co.uniquindio.edu.co.pfp2;
}