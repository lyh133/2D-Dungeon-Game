package unsw.enemy;
import unsw.dungeon.*;


public class EnemyAi{


    private Enemy enemy;
    private Dungeon dungeon;
    private Player player;
    private String lastMove;
    public EnemyAi(Enemy enemy, Dungeon dungeon){
        this.enemy = enemy;
        this.dungeon = dungeon;
        this.player = this.dungeon.getPlayer();
        this.lastMove = "";
    }

    //this loops every Ai_Move_Interval time;
    public void ai_Run(){



        //make decision to chase or run away from player
        if(isplayerKillable())  ai_Chase();
        else                    ai_Escape();

        //handle collision
        if(is_collide_player()) playerCollision();





    }
    

    //ai move one step towards player at a time
    private void ai_Chase(){



        if(player.getX() > enemy.getX()) {
            if(tryMoveRight())  return;
            if(tryMoveUp())     return;
            if(tryMoveDown())   return;
            if(tryMoveLeft())   return;
        }
        if (player.getX() < enemy.getX()) {
            if(tryMoveLeft())   return;
            if(tryMoveUp())     return;
            if(tryMoveDown())   return;
            if(tryMoveRight())  return;
        }
        if(player.getY() > enemy.getY()) {
            if(tryMoveDown())   return;
            if(tryMoveLeft())   return;
            if(tryMoveRight())  return;
            if(tryMoveUp())     return;

        }
        if (player.getY() < enemy.getY()) {
            if(tryMoveUp())     return;
            if(tryMoveLeft())   return;
            if(tryMoveRight())  return;
            if(tryMoveDown())   return;

        }
    }
    //ai move away from player one step at a time
    private void ai_Escape(){
        if(player.getX() > enemy.getX()) {
            if(tryMoveLeft())  return;
            if(tryMoveUp())     return;
            if(tryMoveDown())   return;

        }
        if (player.getX() < enemy.getX()) {
            if(tryMoveRight())   return;
            if(tryMoveUp())     return;
            if(tryMoveDown())   return;
        }
        if(player.getY() > enemy.getY()) {
            if(tryMoveUp())     return;
            if(tryMoveLeft())   return;
            if(tryMoveRight())  return;
            

        }
        if (player.getY() < enemy.getY()) {
            if(tryMoveDown())   return;
            if(tryMoveLeft())   return;
            if(tryMoveRight())  return;
            

        }


    }




    //try move up if successful return true
    private boolean tryMoveUp(){
        //I dont want enemy to stuck in a loop so added lastmove to restrain enemy from going back
        if(canMoveto(enemy.getX(), enemy.getY()-1) && lastMove != "v"){
            enemy.y().set(enemy.getY() - 1);
            lastMove = "^";
            return true; 
        }            
        return false;
    }


    private boolean tryMoveDown(){
        if(canMoveto(enemy.getX(), enemy.getY()+1) && lastMove != "^"){
            enemy.y().set(enemy.getY() + 1); 
            lastMove = "v";
            return true;
        }
        return false;
    }


    private boolean tryMoveRight(){
        if(canMoveto(enemy.getX()+1, enemy.getY()) && lastMove != "<"){
            enemy.x().set(enemy.getX() + 1);  
            lastMove = ">";
            return true;
        }        
        return false;
    }

    private boolean tryMoveLeft(){
        if(canMoveto(enemy.getX()-1, enemy.getY()) && lastMove != ">"){
            enemy.x().set(enemy.getX() - 1); 
            lastMove ="<";
            return true;
        }
        return false;
    }

    // can enemy move to x,y?
    private boolean canMoveto(int x, int y){

       return (!dungeon.EnemyBlockedAtPos(enemy,x,y));
    }
    
    //can the enemy kill player?
    private boolean isplayerKillable(){
        return (player.getisInvincible() == false);
    }

    private boolean is_collide_player(){
        return(player.getX() == enemy.getX() && player.getY() == enemy.getY());
    }


    //handles player collision, stop ai and check goal when enemy dies
    private void playerCollision(){
        //gets killed by player
        if(player.getisInvincible()){
            enemy.die(player.getDungeon());
            //ai_stop();
            dungeon.goalsComplete();
        }
        else if(player.getSwordDurability().get() != 0) {
            player.swinSword(enemy);
            //ai_stop();
            dungeon.goalsComplete();
        }
        else{
            //kills player
            enemy.kill(player);
            //ai_stop();
        }
    }




}