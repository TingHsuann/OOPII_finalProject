import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class GameFlow implements Runnable{
    private DealWithCard dealWithCard=new DealWithCard();
    private Card card;
    private ArrayList<Integer> allCards;
    
    private ArrayList<Integer> foldCards=new ArrayList<>();
    private Player[] players= new Player[5];
    private Player winner;
     
    private int alivePlayerCount=5;

    private boolean oneMoreGame=false;

    private MessageFrame messageFrame;
    private int goldenCoinCount=0;

    private int playerWin=0;
    
    private boolean clockwise=true;
    public GameFlow(){
       
        allCards=new ArrayList<Integer>();
        for(int i=0;i<=50;i++)
        {
            Integer integer=i;
            allCards.add(integer);
        }

        System.out.println("最初50張牌:"+allCards);
        allCards.remove(15);
        allCards.remove(15);
        allCards.remove(15);
        allCards.remove(15);
        System.out.println("去掉炸彈排: "+allCards);



        for(int i=0;i<players.length;i++)
        {
            Player player = new Player(i);
            players[i]=player;
        }

        for(Player player:players)
        {
            ArrayList<Integer> temp=new ArrayList<>();
            temp.add(allCards.get(0));
            player.setHandCard(temp);
            allCards.remove(0);
        }

        dealWithCard.cardShuffled(allCards);

        for(Player player:players)
        {
            dealWithCard.cardDrew(player, allCards, 4);
            System.out.println("player "+player.getPlayerId()+" ->handcard: "+player.getHandCard());
        }


        allCards.add(15);
        allCards.add(16);
        allCards.add(17);
        allCards.add(18);

        dealWithCard.cardShuffled(allCards);
        dealWithCard.cardShuffled(allCards);
        dealWithCard.cardShuffled(allCards);

        System.out.println("初始化完成之大牌堆: "+allCards);
    }

    @Override
    public void run(){
        while(true)
        {
            doNothing(100);
            int deathCount=0;
            for(Player player:players)
            {
                if(player.getPlayerId()==0)
                {
                    if(player.getAlive()==false)
                    {
                        setOneMoreGame(true);
                        setPlayerWin(2);
                        //JOptionPane.showMessageDialog(null,"Game over! You lose~~~");
                        
                        return;
                        //System.exit(0);
                    }
                    continue;
                }

                if(player.getAlive()==false)
                {
                    deathCount++;
                }
                
            }
            
            if(deathCount==4)
            {
                for(Player player:players)
                {
                    if(player.getAlive()==true)
                    {
                        winner=new Player(player.getPlayerId());
                    }
                }
                messageFrame=new MessageFrame("Game over! You win!!! You get "+goldenCoinCount+" gold coins!!!");
                setOneMoreGame(true);
                setPlayerWin(1);
                //JOptionPane.showMessageDialog(null,"Game over! You win!!! You get "+goldenCoinCount+" gold coins!!!");
                System.out.println("Game over! The winner is player "+winner.getPlayerId()+" !");
                
                return;
                //System.exit(0);
            }
/*
            if(deathCount==5)
            {
                System.exit(0);
            }
*/
           
        }
    }
  
    private static void doNothing(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }

}
    



    public ArrayList<Integer> getAllCard(){
        return this.allCards;
    }
    public void setAllCard(ArrayList<Integer> allcard){
        this.allCards=allcard;
    }
    public Player getPlayer(int number){
        int playerIndex=-1;
                for(Player player:players)
                {
                    playerIndex++;
                    if(player.getPlayerId()==number)
                    {
                        break;
                    }
                }
        return players[playerIndex];
    }
    public void addFoldCard(int foldCardNum)
    {
        this.foldCards.add(foldCardNum);
    }
    public Player[] getPlayers()
    {
        return this.players;
    }
    public void setPlayers(Player[] players)
    {
        this.players=players;
    }
    public ArrayList<Integer> getFoldCard()
    {
        return this.foldCards;
    }
    
    public  void alivePlayerCountMinusOne()
    {
        this.alivePlayerCount--;
    }
    public int getAlivePlayerCount()
    {
        return this.alivePlayerCount;
    }

    public void setOneMoreGame(boolean b)
    {
        this.oneMoreGame=b;
    }

    public boolean getOneMoreGame()
    {
        return this.oneMoreGame;
    }
    public int getCoinCount()
    {
        return this.goldenCoinCount;
    }
    public void addOneCoin()
    {
        this.goldenCoinCount++;
    }
    public void setPlayerWin(int i)
    {
        this.playerWin=i;
    }
    public int getPlayerWin()
    {
        return this.playerWin;
    }
    public boolean getClockwise()
    {
        return this.clockwise;
    }
    public void setClockwise(boolean b)
    {
        this.clockwise=b;
    }





    class MessageFrame extends JFrame {
        public MessageFrame(String message) {
            //super("message!");
            setTitle("Message!");
            setLayout(new FlowLayout());
            setSize(1250,750);
            setDefaultCloseOperation(HIDE_ON_CLOSE);

            this.setAlwaysOnTop(true);
            Icon bg = new ImageIcon("Card/gold.png");
            Font f=new Font(null,Font.BOLD,40);
            JLabel picture = new JLabel(message);
            picture.setFont(f);
            picture.setVerticalTextPosition(SwingConstants.BOTTOM);
            picture.setHorizontalTextPosition(SwingConstants.CENTER);
            picture.setIcon(bg);

            add(picture);

            setVisible(true);
            doNothing(3500);
        }
    }
}
