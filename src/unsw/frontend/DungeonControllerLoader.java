package unsw.frontend;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.*;
import unsw.item.*;
import unsw.obstacle.*;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    // Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image doorImage;
	private Image switchImage;
	private Image exitImage;
	private Image swordImage;
	private Image potionImage;
	private Image portalImage;
	private Image treasureImage;
	private Image enemyImage;
    private Image keyImage;
    private Image holeImage;
    private Image arrowImage;
    private Image bowImage;
    private Image reddoorImage;
    private Image bluedoorImage;
    private Image yellowdoorImage;
    private Image redkeyImage;
    private Image bluekeyImage;
    private Image yellowkeyImage;
    private Image bossImage;

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
		switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
		exitImage = new Image((new File("images/exit.png")).toURI().toString());
		swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
		potionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
		portalImage = new Image((new File("images/portal.png")).toURI().toString());
		treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
		enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
		keyImage = new Image((new File("images/key.png")).toURI().toString());
		holeImage = new Image((new File("images/bomb_lit.png")).toURI().toString());
        arrowImage = new Image((new File("images/arrowright.png")).toURI().toString());
        bowImage = new Image((new File("images/bow.png")).toURI().toString());
        yellowdoorImage = new Image((new File("images/yellowdoor.png")).toURI().toString());
        yellowkeyImage = new Image((new File("images/yellowkey.png")).toURI().toString());
        reddoorImage = new Image((new File("images/reddoor.png")).toURI().toString());
        redkeyImage = new Image((new File("images/redkey.png")).toURI().toString());
        bluedoorImage = new Image((new File("images/bluedoor.png")).toURI().toString());
        bluekeyImage = new Image((new File("images/bluekey.png")).toURI().toString());
        bossImage = new Image((new File("images/boss.png")).toURI().toString());
        // explodeImage = new Image((new File("images/bomb_lit.png")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        Player playere = (Player) player;
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
        ChangeListener<Boolean> invincibleListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true) {
					path = "images/godplayer.png";
				} else {
                    if(playere.getSwordDurability().get() != 0 && playere.getBowequipt().getValue() == false){
                        path = "images/playersword.png";
                    }else if(playere.getSwordDurability().get() == 0 && playere.getBowequipt().getValue() == true){
                        path = "images/bowquip.png";
                    }else if(playere.getSwordDurability().get() != 0 && playere.getBowequipt().getValue() == true){
                        path = "images/both.png";
                    }
                    else{
                      path = "images/human_new.png";  
                    }
                    
                    
				}

				view.setImage(new Image((new File(path)).toURI().toString()));
            }
             
		};
        playere.getInvincible().addListener(invincibleListener);

        ChangeListener<Boolean> equiptListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true && playere.getisInvincible() == false && playere.getBowequipt().getValue() == false){
					path = "images/playersword.png";
                } else if(newValue == true && playere.getisInvincible() == false && playere.getBowequipt().getValue() == true){
                    path = "images/both.png";
                }
                else {
					path = "images/human_new.png";
				}

				view.setImage(new Image((new File(path)).toURI().toString()));
            }
             
        };
        playere.getSwordequipt().addListener(equiptListener);
        ChangeListener<Boolean> bowequiptListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				if (newValue == true && playere.getisInvincible() == false && playere.getSwordDurability().get() == 0){
					path = "images/bowquip.png";
                } else if(newValue == true && playere.getisInvincible() == false && playere.getSwordDurability().get() != 0){
                    path = "images/both.png";
                }
                else {
					path = "images/human_new.png";
				}
				view.setImage(new Image((new File(path)).toURI().toString()));
            }
             
        };
        playere.getBowequipt().addListener(bowequiptListener);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    @Override
	public void onLoad(Door door) {

        ImageView view = doorimggenerate(door);

        ChangeListener<Boolean> changeListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				// If the door is open
				if (newValue == true) {
					path = "images/open_door.png";
					// If the door is closed
				} else {
					path = "images/closed_door.png";
				}

				view.setImage(new Image((new File(path)).toURI().toString()));
			}
		};

        door.getState().addListener(changeListener);
        addEntity(door, view);
	}
    
    @Override
	public void onLoad(Switch Switch) {
		ImageView view = new ImageView(switchImage);
		addEntity(Switch, view);

	}

	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);

	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
		addEntity(sword, view);

	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		addEntity(treasure, view);

	}

	@Override
	public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        if(key.getColour().equals("red")){
            view = new ImageView(redkeyImage);
        }
        if(key.getColour().equals("blue")){
            view = new ImageView(bluekeyImage);
        }
        if(key.getColour().equals("yellow")){
            view = new ImageView(yellowkeyImage);
        }



		addEntity(key, view);

	}

	@Override
	public void onLoad(Potion potion) {
		ImageView view = new ImageView(potionImage);
		addEntity(potion, view);

	}

	@Override
	public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
		addEntity(portal, view);

	}

	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		addEntity(enemy, view);
    }
	@Override
	public void onLoadboss(Enemy enemy) {
		ImageView view = new ImageView(bossImage);
		addEntity(enemy, view);
    }
    
    @Override
	public void onLoad(Hole hole) {
		ImageView view = new ImageView(holeImage);
		addEntity(hole, view);
    }
    
    @Override
	public void onLoad(Projectile projectile) {
		ImageView view = new ImageView(arrowImage);
		addEntity(projectile, view);
    }
    @Override
	public void onLoad(Bow bow) {
		ImageView view = new ImageView(bowImage);
		addEntity(bow, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    private ImageView doorimggenerate(Door door){

        if(door.getColour().equals("red")){
            ImageView view = new ImageView(reddoorImage);
            return view;
        }
        if(door.getColour().equals("blue")){
            ImageView view = new ImageView(bluedoorImage);
            return view;
        }
        if(door.getColour().equals("yellow")){
            ImageView view = new ImageView(yellowdoorImage);
            return view;
        }
        if(door.getColour().equals("default")){
            ImageView view = new ImageView(doorImage);
            return view;
        }
        return null;
    }



    /**
     * Set a node in a GridPane to have its position track the position of an entity
     * in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the model
     * will automatically be reflected in the view.
     * 
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * 
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
