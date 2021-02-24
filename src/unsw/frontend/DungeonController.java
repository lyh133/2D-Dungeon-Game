package unsw.frontend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import unsw.frontend.view.PauseScene;
import unsw.frontend.view.VictoryScene;
import unsw.frontend.view.DefeatScene;
import unsw.dungeon.*;
import unsw.item.*;
import unsw.obstacle.*;


/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;
    private Player player;
    private Dungeon dungeon;
    private Timeline timeline;

    @FXML
    private ImageView key;
    @FXML 
    private Button startButton;
    @FXML
    private Pane foreground;
    @FXML
    private GridPane background;
    @FXML
    private Label treasureCount;
    @FXML
    private Label durability;
    @FXML
    private Label potionCount;
    @FXML
    private Label arrowCount;


    private PauseScene pauseScene;
    private VictoryScene victoryScene;
    private DefeatScene defeaftScene;
    private int gameSpeed;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.pauseScene = DungeonLevelLoader.pauseScene;
        this.victoryScene = DungeonLevelLoader.victoryScene;
        this.defeaftScene = DungeonLevelLoader.defeatScene;
        this.gameSpeed = 10;

        //game update 100 time everysecond
        this.timeline = new Timeline(new KeyFrame(Duration.millis(gameSpeed), event -> {

            updateGame();

            
		}));
		this.timeline.setCycleCount(Animation.INDEFINITE);

        initKeyBinding();
    }
   

    @FXML
    public void initialize() {
        //initialise dungeon 
        initInGameUI();
        startGameTime();
        //bind HUD to backend
        treasureCount.textProperty().bind(dungeon.getPlayer().getInventory().getTreasureCount().asString());
        potionCount.textProperty().bind(dungeon.getPlayer().getInventory().getPotionCount().asString());
        durability.textProperty().bind(dungeon.getPlayer().getSwordDurability().asString());
        arrowCount.textProperty().bind(dungeon.getPlayer().getInventory().getArrowCount().asString());


        
        
		
    }

    public void updateGame(){
        updateEnemy();
        updatePlayer();
        updateGoal();
        updateInteraction();
        updateProjectile();
    }

    // time flow of enemy
    private void updateEnemy(){
        // move it by its ai
        for(Enemy enemy : dungeon.getEnemies()){

            if(enemy.getHealth() <= 0){
                enemy.die(dungeon);
            }
            if(enemy.getisAlive()){

                //update enemy by its speed
                enemy.setRefreshPoint(enemy.getRefreshPoint() + enemy.getSpeed());
                if(enemy.getRefreshPoint() >= enemy.getRefreshMax()){
                   dungeon.enemyMove(enemy); 
                   //after enemy moves set its refresh progression to zero
                   enemy.setRefreshPoint(0);
                }

                
            }else{//this is to hide the enemy after it dies
                Entity entity = (Entity)(enemy);
                entity.setX(0);
                entity.setY(19);
            }
        }

            
        
    }
    //time flow of player
    public void updatePlayer(){
        //if player is dead show defeat scene
        if(!dungeon.getPlayer().getisAlive()){
            pauseGameTime();
            defeaftScene.show();
        }


        //if potion in effect then elpase its time
        if(dungeon.getPlayer().getisInvincible()) {
            dungeon.getPlayer().elapseTime(gameSpeed / 10);

            if(dungeon.getPlayer().getInvincibleTime() <= 0) {
                dungeon.getPlayer().setInvincible(false);
                dungeon.getPlayer().getInvincible().setValue(false);
            }
        }
    }    
    public void updateGoal(){
        if(dungeon.goalsComplete()){
            pauseGameTime();
            victoryScene.show();
        }
    }
    // update player collision interatcion
    public void updateInteraction(){
        dungeon.getPlayer().collisionHandler();
    }

    public void updateProjectile(){
        Projectile proj = dungeon.getProjectile();
        if(proj.getInmotion() == false) return;

        ArrayList<Entity> entities = dungeon.getEntitiesAtPos(proj.getX(), proj.getY());
        for(Entity entity : entities){
            if((entity instanceof Wall) ||( entity instanceof Enemy) || (entity instanceof Door)){
                proj.onCollision(entity);
            }

        }


        proj.setRefreshPoint(proj.getRefreshPoint() + proj.getSpeed());
        if(proj.getRefreshPoint() >= proj.getRefreshMax()){
           proj.moveOnce(dungeon.getPlayer());
           //after moves set its refresh progression to zero
           proj.setRefreshPoint(0);
        }


    }



    public void startGameTime(){
        timeline.play();
    }

    public void pauseGameTime(){
        timeline.stop();
    }

    @FXML
    public void handleSetting(){
        pauseGameTime();
        pauseScene.show();

    }


    public void initInGameUI(){


        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                background.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            background.getChildren().add(entity);



        
        
    }

    // creat an arrow at player POS and send it to the direction player is facing
    public void instantiateArrow(Player player, String direction){
        Projectile proj = dungeon.getProjectile();

        proj.setDirection(direction);
        dungeon.getPlayer().getFacingDirection();
        proj.instantiate(player);
        proj.setMotion(true);

    }
    //can only fire if player has bow and arrow and the projectile is not in motion anymore
    private boolean canFire(){
        return (dungeon.getProjectile().getInmotion() == false && dungeon.getPlayer().getBowequipt().get() == true
        && dungeon.getPlayer().getInventory().getArrowCount().get() != 0);
    }
    private void fire(){
        
        String direction = dungeon.getPlayer().getFacingDirection();
        instantiateArrow(dungeon.getPlayer(), direction);
        dungeon.getPlayer().getInventory().getArrowCount().set(dungeon.getPlayer().getInventory().getArrowCount().get() - 1);

    }


    @FXML
    public void handleKeyPress(KeyEvent event) {

        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case DIGIT1:
            Potion potion = (Potion) (dungeon.getPlayer().getInventory().findByName("Potion"));
            dungeon.getPlayer().usePotion(potion);
            
            break;
        case DIGIT2:
            if(!canFire()){
                break;
            }
            fire();

            break;
        default:
            break;
        }
 

    }

    

    private void initKeyBinding(){
        ChangeListener<Boolean> redListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true){
                    path = "images/redkey.png";
                    key.setImage(new Image((new File(path)).toURI().toString()));    
                }
				
            }
             
        };
        dungeon.getPlayer().getInventory().getIs_red().addListener(redListener);
        ChangeListener<Boolean> blueListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true){
                    path = "images/bluekey.png";
                    key.setImage(new Image((new File(path)).toURI().toString()));
                }
				
            }
             
        };
        dungeon.getPlayer().getInventory().getIs_blue().addListener(blueListener);
        ChangeListener<Boolean> yellowListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true){
                    path = "images/yellowkey.png";
                    key.setImage(new Image((new File(path)).toURI().toString()));
                }
				
            }
             
        };
        dungeon.getPlayer().getInventory().getIs_yellow().addListener(yellowListener);
        ChangeListener<Boolean> nokeyListener = new ChangeListener<Boolean>() {
            
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true){
                    path = "images/block.jpg";
                    key.setImage(new Image((new File(path)).toURI().toString()));
                }

				
            }
             
        };
        dungeon.getPlayer().getInventory().getnokey().addListener(nokeyListener);
    }


}

