module co.uniquindio.edu.co.pfp2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires javafx.base;



    opens co.uniquindio.edu.co.pfp2 to javafx.fxml;
    opens co.uniquindio.edu.co.pfp2.viewController to javafx.fxml;

    exports co.uniquindio.edu.co.pfp2;
}
