package unsw.frontend.view;

import unsw.frontend.PauseController;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PauseScene {
    private Stage stage;
    private Scene scene;
    private PauseController controller;
    private DungeonScene dungeonScene;

    public PauseScene(Stage stage)throws IOException{
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseView.fxml"));
        controller = new PauseController();
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root,570,600);
    }
    public DungeonScene getDungeonScene() {
        return dungeonScene;
    }
    public void setDungeonScene(DungeonScene dungeonScene) {
        this.dungeonScene = dungeonScene;
    }
    public PauseController getController() {
        return controller;
    }

    public void show(){
        stage.setScene(scene);
    }

}