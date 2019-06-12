import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Parent root;


    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("scenes/homePageAdmin.fxml"));
        primaryStage.setTitle("FutureWorkForce");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }



}
