package unsw.frontend;

import unsw.frontend.view.DungeonScene;
import unsw.frontend.view.MenuScene;


import javafx.fxml.FXML;


public class DefeatController {
    
    private MenuScene menuScene;
    private LevelController levelController;
    private String dungeon_map;
    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;
    }
    public void setDungeonScene(DungeonScene dungeonScene) {
        this.dungeon_map = dungeonScene.getMap();
    }
    public void setMenuScene(MenuScene menuScene) {
        this.menuScene = menuScene;
    }
    @FXML
	public void initialize() {
        
    }
    @FXML
    public void handleTryagain(){
        levelController.initLevel(dungeon_map);
    }
    @FXML
    public void handleMenu(){
        menuScene.show();
    }


}