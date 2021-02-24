package unsw.frontend.view;

import unsw.frontend.VictoryController;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class VictoryScene {
    private Stage stage;
    private Scene scene;
    private VictoryController controller;

    public VictoryScene(Stage stage)throws IOException{
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VictoryView.fxml"));
       
        controller = new VictoryController();
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root,570,600);
    }
    public VictoryController getController() {
        return controller;
    }
    public void show(){
        stage.setScene(scene);
    }
}