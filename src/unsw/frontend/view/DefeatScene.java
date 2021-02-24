package unsw.frontend.view;

import unsw.frontend.DefeatController;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DefeatScene {
    private Stage stage;
    private Scene scene;
    private DefeatController controller;
    private DungeonScene dungeonScene;

    public DefeatScene(Stage stage)throws IOException{
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DefeatView.fxml"));
        controller = new DefeatController();
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root,570,600);
    }
    public DefeatController getController() {
        return controller;
    }
    public DungeonScene getDungeonScene() {
        return dungeonScene;
    }
    public void show(){
        stage.setScene(scene);
    }
}