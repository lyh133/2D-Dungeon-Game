package unsw.frontend.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.frontend.ExplainController;

public class ExplainScreen {
    private Stage stage;
    private Scene scene;
    private ExplainController controller;

    public ExplainScreen(Stage stage) throws IOException{
        this.stage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExplainView.fxml"));
        controller = new ExplainController();
        loader.setController(controller);        
        Parent root = loader.load();
        scene = new Scene(root);
    }

    

    public void show(){
        stage.setTitle("How to Play");
        stage.setScene(scene);
    }

    public ExplainController getController(){
        return controller;
    }
}
