package unsw.frontend;

import java.io.IOException;

import javafx.application.Application;

import javafx.stage.Stage;

import unsw.frontend.view.*;

public class DungeonApplication extends Application {


    private Stage stage;

    
    @Override
    public void start(Stage primaryStage) throws IOException {

        this.stage = primaryStage;
        primaryStage.setTitle("Dungeon");
        
        PauseScene pauseScene = new PauseScene(stage);
        VictoryScene victoryScene =  new VictoryScene(stage);
        DefeatScene defeatScene = new DefeatScene(stage);
        ExplainScreen explainScreen = new ExplainScreen(stage);
        
        DungeonLevelLoader.setStage(stage);
        DungeonLevelLoader.setPauseScene(pauseScene);
        DungeonLevelLoader.setVictoryScene(victoryScene);
        DungeonLevelLoader.setDefeatScene(defeatScene);

        LevelSelectScene lvlScene = new LevelSelectScene(primaryStage);
        
        MenuScene menuScene = new MenuScene(primaryStage);
        pauseScene.getController().setMenuScene(menuScene);
        victoryScene.getController().setMenuScene(menuScene);
        defeatScene.getController().setMenuScene(menuScene);
        defeatScene.getController().setLevelController(lvlScene.getController());
        pauseScene.getController().setLevelController(lvlScene.getController());
        menuScene.getController().setLevelSelect(lvlScene);
        menuScene.getController().setExplainScreen(explainScreen);
        explainScreen.getController().setMenuScene(menuScene);
        lvlScene.getController().setMenuScene(menuScene);
        menuScene.show();

        
    }
    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }






}
