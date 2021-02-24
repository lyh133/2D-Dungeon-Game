package unsw.frontend;


import java.io.IOException;
import javafx.stage.Stage;
import unsw.frontend.view.*;


public class DungeonLevelLoader {
    
    public static Stage stage;
    public static PauseScene pauseScene;
    public static DungeonScene dungeonScene;
    public static VictoryScene victoryScene;
    public static DefeatScene defeatScene;

    public static DungeonScene loadLevel(String dungeonFileName){

        DungeonScene level = new DungeonScene(stage);
        level.setPauseScene(pauseScene);
        level.setDefeatScene(defeatScene);
        try {
            level.load(dungeonFileName);
        } catch(IOException ie) {
            System.out.println("map file does not exist!");
        }   
        DungeonLevelLoader.dungeonScene = level;
        return level;
    }

    public static void setStage(Stage stage) {
        DungeonLevelLoader.stage = stage;
    }
    public static void setPauseScene(PauseScene pauseScene) {
        DungeonLevelLoader.pauseScene = pauseScene;
    }

    public static void setVictoryScene(VictoryScene victoryScene) {
        DungeonLevelLoader.victoryScene = victoryScene;
    }
    public static void setDefeatScene(DefeatScene defeatScene) {
        DungeonLevelLoader.defeatScene = defeatScene;
    }

}