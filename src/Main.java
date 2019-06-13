import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * This class is for launching the program
 * @author Martin Iversen */
public class Main extends Application {
    Parent root;

/**
 * This method launches the program by creating a primary stage and adding the root we have created to the stage
 * @author Martin Iversen*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("UI/scenes/loginPage.fxml"));
        primaryStage.setTitle("FutureWorkForce");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }



}
