package pl.lodz.p.it.kompo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class About {
    public void showStage(Locale loc) {
        Stage primaryStage = new Stage();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundle",loc);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("About.fxml"),resourceBundle);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
