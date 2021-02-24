package unsw.frontend.view;
import unsw.frontend.*;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScene{

	private Stage stage;
	private DungeonController controller;
	private Scene scene;
	private String map;
	private PauseScene pauseScene;
	private DefeatScene defeatScene;
	public DungeonScene(Stage stage) {
		this.stage = stage;

	}
	public void setDefeatScene(DefeatScene defeatScene) {
		this.defeatScene = defeatScene;
	}
    public void setController(DungeonController controller) {
        this.controller = controller;
	}
	public void setPauseScene(PauseScene pauseScene) {
		this.pauseScene = pauseScene;
	}
	public DungeonController getController() {
		return controller;
	}
	public String getMap() {
		return map;
	}
    public void load(String dungeonFile) throws IOException {
		this.map = dungeonFile;
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeonFile);
		DungeonController controller1 = dungeonLoader.loadController();
		setController(controller1);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller1);
		Parent root = loader.load();
		scene = new Scene(root,570,600);

	}
	public void init(){

		//give this to pasuescene controller so it knows what to resume to;
		pauseScene.getController().setDungeonScene(this);
		defeatScene.getController().setDungeonScene(this);
		scene.getRoot().requestFocus();
		stage.setTitle("Dungen Level");
		stage.setScene(scene);
		stage.show();
	}

}