package co.uniquindio.edu.co.pfp2.viewController;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

public class VisualUtils {

    public static void applyRoleStyles(Parent root, String role){
        if (root == null) return;

        // map role to colors (kept in sync with CSS palette)
        String rectColor = "#E5E7EB"; // neutral default
        String textColor = "#333333";

        switch(role){
            case "user":
                rectColor = "#DCFCE7"; // primario-claro
                textColor = "#117A37"; // primario-oscuro
                break;
            case "repartidor":
                rectColor = "#EAF4FF";
                textColor = "#1E4BB8";
                break;
            case "admin":
                rectColor = "#FFEDEE";
                textColor = "#C92A2A";
                break;
            case "neutral":
            default:
                rectColor = "#E8F6FD";
                textColor = "#2F6FAF";
                break;
        }

        traverseAndApply(root.getChildrenUnmodifiable(), rectColor, textColor);
    }

    private static void traverseAndApply(List<Node> nodes, String rectColor, String textColor){
        for(Node n: nodes){
            if (n instanceof Rectangle){
                ((Rectangle) n).setFill(Color.web(rectColor));
            }
            if (n instanceof Text){
                ((Text) n).setFill(Color.web(textColor));
            }
            if (n instanceof Parent){
                traverseAndApply(((Parent) n).getChildrenUnmodifiable(), rectColor, textColor);
            }
        }
    }
}
