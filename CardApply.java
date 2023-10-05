
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardApply {
    private Random random;
    private GameFlow gameFlow;
    private int currentPlayerID;
    private DealWithCard dealWithCard;

    private int nextBombCardDistance;

    //private boolean useChangePlayerOrgerCard=false;

    private Integer chosenPlayerNumber;

    private boolean pressedButton=false;

    private Player[] tempPlayers;
    private boolean useTempPlayers=false;


    public CardApply(GameFlow gameFlow,int currentPlayerID)
    {
        nextBombCardDistance=0;
        random=new Random();
        this.gameFlow=gameFlow;
        dealWithCard=new DealWithCard(gameFlow);
        this.currentPlayerID=currentPlayerID;
        tempPlayers=new Player[gameFlow.getPlayers().length];
    }
    public CardApply()
    {
        
    }

    public void applyCard(int cardNumber)
    {
        boolean has=false;
        for(int card:gameFlow.getPlayer(currentPlayerID).getHandCard())
        {
            if(cardNumber==card)
            {
                has=true;
            }
        }

        if(has==false)
        {
            System.out.println("Oops no this handCard.");
            System.exit(0);
        }
        
        switch(cardNumber)
        {
            case 0: case 1:case 2: case 3: case 4:case 5:case 6:   //勞工局(萬用牌)
            {
                System.out.println("Player "+currentPlayerID+" use 勞工局");
                //不能自己使用
                break;
            }
            case 7:case 8:              //諂媚(解老闆來了)
            {
                System.out.println("Player "+currentPlayerID+" use 諂媚");
                //不能自己使用      
                break;        
            }
            case 9:case 10:             //風扇(解煤氣外洩)  
            {
                System.out.println("Player "+currentPlayerID+" use 風扇");
                //不能自己使用
                break;
            }
            case 11:case 12:            //逃跑(解坍塌)
            {
                System.out.println("Player "+currentPlayerID+" use 逃跑");
                //不能自己使用
                break;
            }
            case 13:case 14:            //救災(解爆炸)
            {
                System.out.println("Player "+currentPlayerID+" use 救災");
                //不能自己使用       
                break;    

            }
            case 19:case 20:case 21:case 22:case 23:case 24:        //交換(指定玩家交換全部手牌)
            {
                doNothing(300);
                System.out.println("Player "+currentPlayerID+" use 交換");
                if(currentPlayerID!=0){
                    int r = 0;
                    do
                    {
                        r = random.nextInt(5);

                        while(gameFlow.getPlayer(r).getAlive()==false)
                        {
                            r = (int)(Math.random()*5);
                        }
                    }while(r==currentPlayerID);
                    System.out.println("Computer chosen test: "+r);
                    chosenPlayerNumber = Integer.valueOf(r);
                }
                else{
                    JFrame chooseTargetFrame = new JFrame();
                        //JDialog jd = new JDialog(chooseTargetFrame);

                        chooseTargetFrame.setLayout(new BorderLayout());

                        chooseTargetFrame.setBounds(500, 300, 400, 300);

                        JLabel goalLabel = new JLabel("Choose one player you want to exchange!");

                        int buildButtonCount=0;

                        int test=gameFlow.getAlivePlayerCount()-1;
                        System.out.println("按鈕數量: "+test);
                        Integer[] otherPlayerIDs=new Integer[gameFlow.getAlivePlayerCount()-1];

                        

                        int IDindex=0;
                        for(int i=0;i<otherPlayerIDs.length;i++)
                        {
                            IDindex++;
                            while(gameFlow.getPlayer(IDindex).getAlive()==false)
                            {
                                IDindex++;
                            }
                                
                                
                            otherPlayerIDs[i]=IDindex;
                                
                        }

                        


                        JButton[] otherPlayerIButtons=new JButton[otherPlayerIDs.length];
                        JPanel buttonPanel=new JPanel();
                        buttonPanel.setLayout(new GridLayout(2,2));
                        while(pressedButton==false)
                        {
                            for(int i=0;i<otherPlayerIDs.length;i++)
                            {
                                if(buildButtonCount==otherPlayerIDs.length)
                                {
                                    break;
                                }
                                otherPlayerIButtons[i]=new JButton(String.valueOf(otherPlayerIDs[i]));
                                buildButtonCount++;
                                otherPlayerIButtons[i].addActionListener(new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent e) 
                                    {
                                        pressedButton=true;
                                        String pressed=e.getActionCommand();
                                        chosenPlayerNumber=Integer.parseInt(pressed);
                                        chooseTargetFrame.setVisible(false);
                                        
                                    }
                                });
                                
                                buttonPanel.add(otherPlayerIButtons[i]);
                            
                            }
                            
                            
                            chooseTargetFrame.add(buttonPanel,BorderLayout.CENTER);
                            

                            chooseTargetFrame.add(goalLabel,BorderLayout.NORTH);
                            
                            chooseTargetFrame.setVisible(true);
                        }   
                        chooseTargetFrame.dispose();
                    }

                    ArrayList<Integer> currentPlayerHandCard=gameFlow.getPlayer(currentPlayerID).getHandCard();
        
                    int cardNumberIndex=0;
                    for(int i=0;i<currentPlayerHandCard.size();i++)
                    {
                        if(currentPlayerHandCard.get(i)==cardNumber)
                        {
                            cardNumberIndex=i;
                        }
                    }
                    System.out.println("removeCardIndex: "+cardNumberIndex);
                    System.out.println("You exchange your handcards with Player "+chosenPlayerNumber+"...");
                    
                    currentPlayerHandCard.remove(cardNumberIndex);
                    gameFlow.getPlayer(currentPlayerID).setHandCard(currentPlayerHandCard);
                    gameFlow.addFoldCard(cardNumber);
                    
                    ArrayList<Integer> tempHandCard=gameFlow.getPlayer(chosenPlayerNumber).getHandCard();
                    gameFlow.getPlayer(chosenPlayerNumber).setHandCard(gameFlow.getPlayer(currentPlayerID).getHandCard());
                    gameFlow.getPlayer(currentPlayerID).setHandCard(tempHandCard);
                    
                return;
                
                //break;
            }
            case 25:case 26:case 27:case 28:case 29:case 30:        //索取(指定玩家索取一張手牌)
            {
                doNothing(300);
                System.out.println("Player "+currentPlayerID+" use 索取");

                if(currentPlayerID!=0){
                    int r = 0;
                    
                    do
                    {
                        r = random.nextInt(5);;

                        while(gameFlow.getPlayer(r).getHandCard().size()==0)
                        {
                            r = (int)(Math.random()*5);
                        }
                    }while(r==currentPlayerID);
                    System.out.println("Computer chosen test: "+r);
                    chosenPlayerNumber = Integer.valueOf(r);
                }
                else{
                    JFrame chooseTargetFrame = new JFrame();
                    

                    chooseTargetFrame.setLayout(new BorderLayout());

                    chooseTargetFrame.setBounds(500, 300, 400, 300);

                    JLabel goalLabel = new JLabel("Choose one player you want to ask!");

                    int buildButtonCount=0;

                    int test=gameFlow.getAlivePlayerCount()-1;
                    System.out.println("按鈕數量: "+test);
                    Integer[] otherPlayerIDs=new Integer[gameFlow.getAlivePlayerCount()-1];

                 

                    int IDindex=0;
                    for(int i=0;i<otherPlayerIDs.length;i++)
                    {
                        IDindex++;
                        while(gameFlow.getPlayer(IDindex).getAlive()==false)
                        {
                            IDindex++;
                        }
                            
                            
                        otherPlayerIDs[i]=IDindex;
                            
                    }



                    JButton[] otherPlayerIButtons=new JButton[otherPlayerIDs.length];
                    JPanel buttonPanel=new JPanel();
                    buttonPanel.setLayout(new GridLayout(2,2));
                    while(pressedButton==false)
                    {
                        for(int i=0;i<otherPlayerIDs.length;i++)
                        {
                            if(buildButtonCount==otherPlayerIDs.length)
                            {
                                break;
                            }
                            otherPlayerIButtons[i]=new JButton(String.valueOf(otherPlayerIDs[i]));
                            buildButtonCount++;
                            otherPlayerIButtons[i].addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e) 
                                {
                                    pressedButton=true;
                                    String pressed=e.getActionCommand();
                                    chosenPlayerNumber=Integer.parseInt(pressed);
                                    chooseTargetFrame.setVisible(false);
                                    
                                }
                            });
                            
                            buttonPanel.add(otherPlayerIButtons[i]);
                        
                        }

                        
                        chooseTargetFrame.add(buttonPanel,BorderLayout.CENTER);
                        

                        chooseTargetFrame.add(goalLabel,BorderLayout.NORTH);
                        
                        chooseTargetFrame.setVisible(true);
                    }   
                    chooseTargetFrame.dispose();
                }


                ArrayList<Integer> currentPlayerHandCard=gameFlow.getPlayer(currentPlayerID).getHandCard();
    
                int cardNumberIndex=0;
                for(int i=0;i<currentPlayerHandCard.size();i++)
                {
                    if(currentPlayerHandCard.get(i)==cardNumber)
                    {
                        cardNumberIndex=i;
                    }
                }
                System.out.println("removeCardIndex: "+cardNumberIndex);
                System.out.println("You get a card from "+chosenPlayerNumber);
                
                currentPlayerHandCard.remove(cardNumberIndex);
                gameFlow.getPlayer(currentPlayerID).setHandCard(currentPlayerHandCard);
                gameFlow.addFoldCard(cardNumber);



                System.out.println("RandomTest: "+gameFlow.getPlayer(chosenPlayerNumber).getHandCard().size());

                int theRandomCardIndex=random.nextInt(gameFlow.getPlayer(chosenPlayerNumber).getHandCard().size());
                int theRandomCardNumber=gameFlow.getPlayer(chosenPlayerNumber).getHandCard().get(theRandomCardIndex);

                ArrayList<Integer> currentPlayerHandCard2=gameFlow.getPlayer(currentPlayerID).getHandCard();
                currentPlayerHandCard2.add(theRandomCardNumber);
                gameFlow.getPlayer(currentPlayerID).setHandCard(currentPlayerHandCard2);

                ArrayList<Integer> chosenPlayerHandCard=gameFlow.getPlayer(chosenPlayerNumber).getHandCard();
                chosenPlayerHandCard.remove(theRandomCardIndex);
                gameFlow.getPlayer(chosenPlayerNumber).setHandCard(chosenPlayerHandCard);
                System.out.println("The gotten card is "+theRandomCardNumber);
                return;
            }
            case 31:case 32:case 33:case 34:        //加班
            {
                System.out.println("Player "+currentPlayerID+" use 加班");
                if(currentPlayerID!=0){
                    int r = 0;
                    do
                    {
                        r = random.nextInt(5);;

                        while(gameFlow.getPlayer(r).getAlive()==false)
                        {
                            r = (int)(Math.random()*5);
                        }
                    }while(r==currentPlayerID);

                    System.out.println("Computer chosen test: "+r);
                    chosenPlayerNumber = Integer.valueOf(r);
                }
                else{
                    JFrame chooseTargetFrame = new JFrame();

                    chooseTargetFrame.setLayout(new BorderLayout());

                    chooseTargetFrame.setBounds(500, 300, 400, 300);

                    JLabel goalLabel = new JLabel("Choose one player you want to work overtime!");

                    int buildButtonCount=0;

                    int test=gameFlow.getAlivePlayerCount()-1;
                    System.out.println("按鈕數量: "+test);
                    Integer[] otherPlayerIDs=new Integer[gameFlow.getAlivePlayerCount()-1];

                    


                    int IDindex=0;
                    for(int i=0;i<otherPlayerIDs.length;i++)
                    {
                        IDindex++;
                        while(gameFlow.getPlayer(IDindex).getAlive()==false)
                        {
                            IDindex++;
                        }
                            
                            
                        otherPlayerIDs[i]=IDindex;
                            
                    }

                   


                    JButton[] otherPlayerIButtons=new JButton[otherPlayerIDs.length];
                    JPanel buttonPanel=new JPanel();
                    buttonPanel.setLayout(new GridLayout(2,2));
                    while(pressedButton==false)
                    {
                        for(int i=0;i<otherPlayerIDs.length;i++)
                        {
                            if(buildButtonCount==otherPlayerIDs.length)
                            {
                                break;
                            }
                            otherPlayerIButtons[i]=new JButton(String.valueOf(otherPlayerIDs[i]));
                            buildButtonCount++;
                            otherPlayerIButtons[i].addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e) 
                                {
                                    pressedButton=true;
                                    String pressed=e.getActionCommand();
                                    chosenPlayerNumber=Integer.parseInt(pressed);
                                    chooseTargetFrame.setVisible(false);
                                    
                                }
                            });
                            
                            buttonPanel.add(otherPlayerIButtons[i]);
                        
                        }

                        
                        chooseTargetFrame.add(buttonPanel,BorderLayout.CENTER);
                        

                        chooseTargetFrame.add(goalLabel,BorderLayout.NORTH);
                        
                        chooseTargetFrame.setVisible(true);
                    }   
                    chooseTargetFrame.dispose();
                }


                

                ArrayList<Integer> currentPlayerHandCard=gameFlow.getPlayer(currentPlayerID).getHandCard();
    
                int cardNumberIndex=0;
                for(int i=0;i<currentPlayerHandCard.size();i++)
                {
                    if(currentPlayerHandCard.get(i)==cardNumber)
                    {
                        cardNumberIndex=i;
                    }
                }
                System.out.println("removeCardIndex: "+cardNumberIndex);
                System.out.println("You let Player "+chosenPlayerNumber+" be overtime....");

                currentPlayerHandCard.remove(cardNumberIndex);
                gameFlow.getPlayer(currentPlayerID).setHandCard(currentPlayerHandCard);
                gameFlow.addFoldCard(cardNumber);
                
                if(gameFlow.getClockwise()==true)
                {
                    int playerID=0;
                    for(int i=0;i<tempPlayers.length;i++)
                    {
                        playerID=chosenPlayerNumber+i;
                        if(playerID==5)
                        {
                            playerID=0;
                        }
                        else if(playerID==6)
                        {
                            playerID=1;
                        }
                        else if(playerID==7)
                        {
                            playerID=2;
                        }
                        else if(playerID==8)
                        {
                            playerID=3;
                        }

                        tempPlayers[i]=gameFlow.getPlayer(playerID);
                    }
                }
                else if(gameFlow.getClockwise()==false)
                {
                    int playerID=0;
                    for(int i=0;i<tempPlayers.length;i++)
                    {
                        playerID=chosenPlayerNumber-i;
                        if(playerID==-1)
                        {
                            playerID=4;
                        }
                        else if(playerID==-2)
                        {
                            playerID=3;
                        }
                        else if(playerID==-3)
                        {
                            playerID=2;
                        }
                        else if(playerID==-4)
                        {
                            playerID=1;
                        }

                        tempPlayers[i]=gameFlow.getPlayer(playerID);
                    }
                }
                
                //gameFlow.setPlayers(tempPlayers);
                useTempPlayers=true;
                //useChangePlayerOrgerCard=true;
                return;
            }
            case 35:case 36:case 37:case 38:        //下班
            {
                System.out.println("Player "+currentPlayerID+" use 下班");

                break;
            }
            case 39:case 40:case 41:case 42:        //混亂
            {
                System.out.println("Player "+currentPlayerID+" use 混亂");

                dealWithCard.cardShuffled(gameFlow.getAllCard());

                System.out.println("混亂後大牌堆: "+gameFlow.getAllCard());
                break;
            }
            case 43:case 44:case 45:case 46:        //偷懶
            {
                System.out.println("Player "+currentPlayerID+" use 偷懶");

                ArrayList<Integer> currentAllCards=gameFlow.getAllCard();
                
                for(int card:currentAllCards)
                {
                    nextBombCardDistance++;
                    if(card==15||card==16||card==17||card==18)
                    {
                        break;
                    }
                }

                if(currentPlayerID==0)
                {
                    JOptionPane.showMessageDialog(null, "下一張炸彈牌在第 "+nextBombCardDistance);
                }
                
                
                break;
            }
            case 47:case 48:case 49:case 50:        //午休
            {
                System.out.println("Player "+currentPlayerID+" use 午休");
                
                
                if(gameFlow.getClockwise()==true)
                {

                    int tempPlayerID=currentPlayerID;
                    for(int i=0;i<gameFlow.getPlayers().length;i++)
                    {
                        tempPlayerID--;
                        if(tempPlayerID<0)
                        {
                            tempPlayerID=4;
                        }
                        tempPlayers[i]=new Player(tempPlayerID);
                    }

                    gameFlow.setClockwise(false);
                }
                else if(gameFlow.getClockwise()==false)
                {
                    int tempPlayerID=currentPlayerID;
                    for(int i=0;i<gameFlow.getPlayers().length;i++)
                    {
                        tempPlayerID++;
                        if(tempPlayerID>4)
                        {
                            tempPlayerID=0;
                        }
                        tempPlayers[i]=new Player(tempPlayerID);
                    }
                    gameFlow.setClockwise(true);
                }

                useTempPlayers=true;
                break;
            }
        }

        gameFlow.addFoldCard(cardNumber);

        ArrayList<Integer> currentPlayerHandCard=gameFlow.getPlayer(currentPlayerID).getHandCard();
        
        int cardNumberIndex=8888;
        for(int i=0;i<currentPlayerHandCard.size();i++)
        {
            if(currentPlayerHandCard.get(i)==cardNumber)
            {
                cardNumberIndex=i;
            }
        }
        System.out.println("removeCardIndex: "+cardNumberIndex);
        
        currentPlayerHandCard.remove(cardNumberIndex);
        gameFlow.getPlayer(currentPlayerID).setHandCard(currentPlayerHandCard);


        System.out.println("棄牌堆: "+gameFlow.getFoldCard());
    }






    public int getBombCardDistance()
    {
        return this.nextBombCardDistance;
    }
    /*
    public boolean getuseChangePlayerOrgerCard()
    {
        return this.useChangePlayerOrgerCard;
    }
    public void setuseChangePlayerOrgerCard(boolean b)
    {
        this.useChangePlayerOrgerCard=b;
    }

    */
    public Player[] getTempPlayers()
    {
        return this.tempPlayers;
    }
    public boolean getUseTempPlayers()
    {
        return this.useTempPlayers;
    }
    public void setUseTempPlayers(boolean b)
    {
        this.useTempPlayers=b;
    }
    private static void doNothing(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }

    }
}
