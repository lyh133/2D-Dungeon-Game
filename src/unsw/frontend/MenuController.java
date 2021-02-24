package unsw.frontend;

import unsw.frontend.view.ExplainScreen;
import unsw.frontend.view.LevelSelectScene;

import javafx.fxml.FXML;




public class MenuController {

    LevelSelectScene levelSelect;
    ExplainScreen explainScreen;
    


    public void setLevelSelect(LevelSelectScene levelSelect) {
        this.levelSelect = levelSelect;
    }
    public void setExplainScreen(ExplainScreen explainScreen) {
        this.explainScreen = explainScreen;
    }

        
    @FXML
    public void handleStart(){
       levelSelect.show();
    } 

    @FXML
    public void handleExplain(){
        explainScreen.show();
    } 
    
    @FXML
    public void handleExit(){
        System.exit(0);
    }
}