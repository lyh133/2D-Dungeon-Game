package unsw.frontend;

import unsw.frontend.view.MenuScene;
import javafx.fxml.FXML;

public class VictoryController {
    
    private MenuScene menuScene;
    @FXML
	public void initialize() {
        
    }

    @FXML
    public void handlevMenu(){
        menuScene.show();
    }
    public void setMenuScene(MenuScene menuScene) {
        this.menuScene = menuScene;
    }
}