package unsw.frontend;

import java.util.ArrayList;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.frontend.view.*;




public class LevelController {

    private ArrayList<String> levelNames;
    private DungeonScene dungeonScene;
    private MenuScene menuScene;
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    private ImageView defaultImage;
    private StringProperty levelSelect;

    public LevelController(){
        levelSelect = new SimpleStringProperty();
    }

    @FXML
	public void initialize() {

        levelNames = new ArrayList<>();
        levelNames.add("boulder2.json");
        
        cb.setItems(FXCollections.observableArrayList("maze", "boulders", "advanced","dun1" ));

        levelSelect.bindBidirectional(cb.valueProperty());

        
        ChangeListener<String> changeListener = new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String old, String newValue) {
                String path = "file:./examples/" + newValue + ".png";
        
                

                Image newImage = new Image(path);

                defaultImage.setImage(newImage);
                
            }
            
        };

        cb.valueProperty().addListener(changeListener);

        cb.setValue("maze");

        


    }

    public void setMenuScene(MenuScene menuScene) {
        this.menuScene = menuScene;
    }


    @FXML
    public void playLevelOne() {


        initLevel(levelSelect.getValue() + ".json");
 
        
    }

    @FXML
    public void handleBack(){
        menuScene.show();

    }

    public void initLevel(String dungeonFileName) {
        
        this.dungeonScene = DungeonLevelLoader.loadLevel(dungeonFileName);

        dungeonScene.init();
    }
}