module co.uniquindio.edu.co.pfp2 {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.base;

    requires java.compiler;
    requires java.desktop;
    requires java.logging;
    requires com.github.librepdf.openpdf;

    opens co.uniquindio.edu.co.pfp2 to javafx.fxml;
    opens co.uniquindio.edu.co.pfp2.viewController to javafx.fxml;

    exports co.uniquindio.edu.co.pfp2;
    exports co.uniquindio.edu.co.pfp2.model;
}
