import java.util.ArrayList;
public class Player {
    private boolean alive = true;
    private int PLAYERID;
    private ArrayList<Integer> handCard = new ArrayList<Integer>();
    

    public Player (int playerId){
        this.PLAYERID = playerId;
    }
  

    
    public int getPlayerId(){
        return PLAYERID;
    }
    public boolean getAlive(){
        return alive;
    }
    public void setAlive(boolean a){
        alive= a;
    }
    public ArrayList<Integer> getHandCard(){
        return handCard;
    }
    public void setHandCard(ArrayList<Integer> handcard){
        handCard=handcard;
    }
    public int HandCardNum(){
        return getHandCard().size();
    }
 
}
