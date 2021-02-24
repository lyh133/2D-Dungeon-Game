package unsw.frontend;

import javafx.fxml.FXML;
import unsw.frontend.view.MenuScene;

public class ExplainController {


    private MenuScene menuScene;

    @FXML
    public void initialize(){
        
    }

    public void setMenuScene(MenuScene menuScene){
        this.menuScene = menuScene;
    }

    @FXML
    public void handleBack(){
        menuScene.show();

    }


	
   

    
}