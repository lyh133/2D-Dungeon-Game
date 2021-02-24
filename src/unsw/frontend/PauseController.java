package unsw.frontend;

import unsw.frontend.view.DungeonScene;
import unsw.frontend.view.MenuScene;


import javafx.fxml.FXML;



public class PauseController {


    private DungeonScene dungeonScene;
    private MenuScene menuScene;
    private String dungeon_map;
    private LevelController levelController;
    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;
    }
    public void setDungeonScene(DungeonScene dungeonScene) {
        this.dungeonScene = dungeonScene;
        this.dungeon_map = dungeonScene.getMap();
    }
    public void setMenuScene(MenuScene menuScene) {
        this.menuScene = menuScene;
    }


    @FXML
	public void initialize() {
        
    }
    @FXML
    public void handleResume(){
        dungeonScene.getController().startGameTime();
        dungeonScene.init();
    }
    @FXML
    public void handleBack(){
        menuScene.show();
    }
    @FXML
    public void handleRestart(){
        levelController.initLevel(dungeon_map);
    }
    
}