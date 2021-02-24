package unsw.frontend.view;
import unsw.frontend.LevelController;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelSelectScene {

    private Stage stage;
    private Scene scene;
    public LevelController controller;
    public LevelSelectScene(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelSelectView.fxml"));
        controller = new LevelController();
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root);

    }
    public void show(){
        stage.setTitle("Level Select");
        stage.setScene(scene);
    }
    public LevelController getController() {
        return controller;
    }
    
}