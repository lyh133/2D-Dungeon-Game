package unsw.frontend.view;


import unsw.frontend.MenuController;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MenuScene{

    private Stage stage;
    private Scene scene;
    private MenuController controller;
    public MenuScene(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
        controller = new MenuController();
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);


    }

    public MenuController getController() {
        return controller;
    }
    public void show(){
        stage.setTitle("Dungeon Menu");
        stage.setScene(scene);
        stage.show();
    }
}