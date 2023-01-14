package pl.lodz.p.it.kompo.view;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Controler Controler = new Controler();
        Controler.showStage();
    }

}