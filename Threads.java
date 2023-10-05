import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Threads implements Runnable {
    private Thread gameFlowThread;
    private GameFlow gameFlow;
    private CardApply cardApply = new CardApply();
    private DealWithCard dealwithcard;
    AutoStrategy autoStrategy;
    private boolean computerPhase;
    boolean tempPlayerMode = false;
    boolean[] whetherDeathMinus = { false, false, false, false, false };
    int[] playerIDs = new int[5];
    private Integer drawMode = 0;
    private Integer UseCardNumber;
    private int nowPlayer = 0;

    public Threads(GameFlow gameFlow) {
        super();

        this.gameFlow = gameFlow;
        dealwithcard = new DealWithCard(gameFlow);
        gameFlowThread = new Thread(gameFlow);
        gameFlowThread.start();

    }

    @Override
    public void run() {
        DealWithRound();
    }

    public void DealWithRound() {

        outer: while (true) {
            if (cardApply.getUseTempPlayers() == true) {

                Player[] players = cardApply.getTempPlayers();
                for (int i = 0; i < gameFlow.getPlayers().length; i++) {
                    playerIDs[i] = players[i].getPlayerId();
                }
                cardApply.setUseTempPlayers(false);

            } else if (tempPlayerMode == false) {

                Player[] players = gameFlow.getPlayers();

                for (int i = 0; i < gameFlow.getPlayers().length; i++) {
                    playerIDs[i] = players[i].getPlayerId();
                }

            }

            System.out.print("目前玩家id順序: ");

            for (int i = 0; i < playerIDs.length; i++) {
                System.out.print(playerIDs[i] + " , ");
            }
            System.out.println();

            internal: for (int roundPlayer : playerIDs) {

                if(gameFlow.getOneMoreGame()==true)
                {
                    System.out.println("終止thread");
                    //JOptionPane.showMessageDialog(null,"請點擊左上Button決定下一步動作");
                    return;
                }



                dealwithcard = new DealWithCard(gameFlow);

                if (cardApply.getUseTempPlayers() == true) {

                    tempPlayerMode = true;
                    continue outer;
                }

                System.out.println("---------------------------------------------");
                System.out.println("目前玩家: " + roundPlayer);
                System.out.println("目前玩家手牌: " + gameFlow.getPlayer(roundPlayer).getHandCard());

                if (roundPlayer == 0) {
                    setComputerPhase(false);
                    setnowPlayer(0);
                    if (gameFlow.getPlayer(roundPlayer).getAlive() == true) {
                        gameFlow.addOneCoin();
                        // Scanner input=new Scanner(System.in);
                        // System.out.println("Do you want to draw a card? If true input 1,if you want
                        // to use card input 2.");

                        // int whetherDraw=input.nextInt();
                        setDrawMode(0);
                        // setComputerPhase(false);
                        while (getDrawMode() == 0) {
                            doNothing(500);
                            if (getDrawMode() == 1) {
                                System.out.println("drawMode1Test");
                                dealwithcard.cardDrew(gameFlow.getPlayer(roundPlayer), gameFlow.getAllCard());
                                if (gameFlow.getPlayer(roundPlayer).getAlive() == false) {

                                    if (whetherDeathMinus[roundPlayer] == false) {
                                        gameFlow.alivePlayerCountMinusOne();
                                        whetherDeathMinus[roundPlayer] = true;
                                    }

                                    System.out.println("Player " + roundPlayer + " died.");
                                    
                                }


                                if(gameFlow.getOneMoreGame()==true)
                                {
                                    System.out.println("終止thread");
                                    //JOptionPane.showMessageDialog(null,"請點擊左上Button決定下一步動作");
                                    return;
                                }


                                System.out.println("抽牌後玩家手牌: " + gameFlow.getPlayer(roundPlayer).getHandCard());
                                System.out.println("抽牌後大牌堆: " + gameFlow.getAllCard());
                                /*
                                 * System.out.println("抽牌中...");
                                 * dealwithcard.cardDrew(gameFlow.getPlayer(roundPlayer),gameFlow.getAllCard());
                                 * System.out.println("抽牌後玩家手牌: "+gameFlow.getPlayer(roundPlayer).getHandCard())
                                 * ;
                                 * 
                                 * System.out.println("抽牌後大牌堆: "+gameFlow.getAllCard());
                                 */
                            } else if (getDrawMode() == 2) {
                                System.out.println("drawMode2Test");
                                int useCard = this.UseCardNumber;
                                System.out.println("The card used is: " + useCard);
                                cardApply = new CardApply(gameFlow, roundPlayer);
                                cardApply.applyCard(useCard);
                                System.out.println("用牌後玩家手牌: " + gameFlow.getPlayer(roundPlayer).getHandCard());
                                /*
                                 * System.out.println("Which card do you want to use?");
                                 * int useCard = input.nextInt();
                                 * if((useCard>=0&&useCard<=6)||(useCard>=7&&useCard<=14))
                                 * {
                                 * System.out.println("你不能直接用這張牌");
                                 * System.exit(0);
                                 * }
                                 * System.out.println("The card used is: "+ useCard);
                                 * 
                                 * cardApply=new CardApply(gameFlow, roundPlayer);
                                 * 
                                 * cardApply.applyCard(useCard);
                                 * 
                                 * System.out.println("用牌後玩家手牌: "+gameFlow.getPlayer(roundPlayer).getHandCard())
                                 * ;
                                 */
                            }

                        }
                    } else {
                        System.out.println("Player " + roundPlayer + " died.");
                    }

                }

                if(gameFlow.getOneMoreGame()==true)
                {
                    System.out.println("終止thread");
                    //JOptionPane.showMessageDialog(null,"請點擊左上Button決定下一步動作");
                    return;
                }



                if (roundPlayer >= 1 && roundPlayer <= 4) {
                    setComputerPhase(true);
                    setnowPlayer(roundPlayer);
                    if (gameFlow.getPlayer(roundPlayer).getAlive() == true) {/*
                                                                              * System.out.println("抽牌中...");
                                                                              * dealwithcard.cardDrew(gameFlow.getPlayer
                                                                              * (roundPlayer),gameFlow.getAllCard());
                                                                              * System.out.println("抽牌後玩家手牌: "+gameFlow.
                                                                              * getPlayer(roundPlayer).getHandCard());
                                                                              * System.out.println("抽牌後大牌堆: "+gameFlow.
                                                                              * getAllCard());
                                                                              */
                        cardApply = new CardApply(gameFlow, roundPlayer);
                        autoStrategy = new AutoStrategy(gameFlow.getPlayer(roundPlayer), cardApply);
                        autoStrategy.useCard(gameFlow.getPlayer(roundPlayer), gameFlow);

                        if(gameFlow.getOneMoreGame()==true)
                        {
                            System.out.println("終止thread");
                            //JOptionPane.showMessageDialog(null,"請點擊左上Button決定下一步動作");
                            return;
                        }



                        System.out.println("抽牌後大牌堆: " + gameFlow.getAllCard());
                        if (gameFlow.getPlayer(roundPlayer).getAlive() == false) {
                            if (whetherDeathMinus[roundPlayer] == false) {
                                gameFlow.alivePlayerCountMinusOne();
                                whetherDeathMinus[roundPlayer] = true;
                            }

                            System.out.println("Player " + roundPlayer + " died.");
                            JOptionPane.showMessageDialog(null, "Player "+roundPlayer+" 死亡QQ");
                        }
                    } else {
                        System.out.println("Player " + roundPlayer + " died.");
                    }

                }

                if(gameFlow.getOneMoreGame()==true)
                {
                    System.out.println("終止thread");
                    //JOptionPane.showMessageDialog(null,"請點擊左上Button決定下一步動作");
                    return;
                }

                
                System.out.println("---------------------------------------------");
                doNothing(1500);
            }

            
            if(gameFlow.getOneMoreGame()==true)
            {
                System.out.println("終止thread");
                //JOptionPane.showMessageDialog(null,"請點擊左上按鈕決定下一步動作");
                return;
            }
        }

    }

    private static void doNothing(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }

    }

    public int getnowPlayer() {
        return nowPlayer;
    }

    public void setnowPlayer(int nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public DealWithCard getDealWithCard() {
        return this.dealwithcard;
    }

    public GameFlow getGameFlow() {
        return this.gameFlow;
    }

    public void setDrawMode(Integer integer) {
        this.drawMode = integer;
    }

    public Integer getDrawMode() {
        return this.drawMode;
    }

    public void setUseCard(Integer useCardNumber) {
        this.UseCardNumber = useCardNumber;
    }

    public boolean getComputerPhase() {
        return this.computerPhase;
    }

    public void setComputerPhase(boolean computerPhase) {
        this.computerPhase = computerPhase;
    }

}
