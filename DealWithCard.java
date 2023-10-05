import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class DealWithCard {
    private GameFlow gameFlow;
    private CardApply cardApply;
    private int roundPlayer;
    private Random random = new Random();

    public DealWithCard(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    public DealWithCard() {

    }

    public void cardShuffled(ArrayList<Integer> arrayList) {
        Collections.shuffle(arrayList);
    }

    /*
     * public void cardDrew(Group9_Project_Player player,ArrayList<Integer>
     * allCards){
     * Random random=new Random();
     * int allCardSize=allCards.size();
     * int theRandomIndex=random.nextInt(allCardSize);
     * int theRandomCard=allCards.get(theRandomIndex);
     * player.setHandCard(player.getHandCard().add(theRandomCard));
     * allCards.remove(theRandomIndex);
     * }
     */
    public void cardDrew(Player player, ArrayList<Integer> allCards, int drewNumber) {

        for (int i = 0; i < drewNumber; i++) {
            int theFirstCard = allCards.get(0);
            ArrayList<Integer> currentHandCard = player.getHandCard();

            currentHandCard.add(theFirstCard);
            player.setHandCard(currentHandCard);
            allCards.remove(0);
        }

    }

    public void cardDrew(Player player, ArrayList<Integer> allCards) {
        if (allCards.size() == 0) {
            System.out.println("牌抽完了");
            System.exit(0);
        }

        roundPlayer = player.getPlayerId();
        cardApply = new CardApply(gameFlow, roundPlayer);

        int theFirstCard = allCards.get(0);
        ArrayList<Integer> currentHandCard = player.getHandCard();
        if (theFirstCard == 15) {
            System.out.println("Player " + player.getPlayerId() + " 抽到 Boss來了");
            MessageFrame messageFrame = new MessageFrame(15, "Player " + player.getPlayerId() + " 抽到");
            boolean alive = false;
            boolean hasCustomizedCard=false;
            boolean hasUniversalCard=false;

            outer:for(int card : currentHandCard){
                if(card == 7){
                    hasCustomizedCard=true;
                    //break;
                }
                else if(card == 8){
                    hasCustomizedCard=true;
                    //break;
                }
                else{
                    for (int j = 0; j <= 6; j++){
                        if (card == j){
                            hasUniversalCard=true;
                            //break outer;
                        }
                    }
                }
            }

            if(hasCustomizedCard==true){
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)==7){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有諂媚，逃過一劫");
                        alive = true;
                        cardApply.applyCard(7);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 15);
                        allCards.remove(0);
                        break;
                    }
                    else if(currentHandCard.get(j)==8){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有諂媚，逃過一劫");
                        alive = true;
                        cardApply.applyCard(8);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 15);
                        allCards.remove(0);
                        break;
                    }
                }
            }
            else if(hasCustomizedCard==false&&hasUniversalCard==true){

                int universalCardNumber=999;
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)>=0&&currentHandCard.get(j)<=6){
                        universalCardNumber=currentHandCard.get(j);
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                        alive = true;
                        cardApply.applyCard(universalCardNumber);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 15);
                        allCards.remove(0);
                        break;
                    }
                }
            }


/*
            outer: for (int card : currentHandCard) {
                if (card == 7) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有諂媚，逃過一劫");
                    alive = true;
                    cardApply.applyCard(7);
                    //allCards.remove(0);

                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 15);
                    allCards.remove(0);
                    break;
                } else if (card == 8) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有諂媚，逃過一劫");
                    alive = true;
                    cardApply.applyCard(8);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 15);
                    allCards.remove(0);
                    break;
                } else {
                    for (int j = 0; j <= 6; j++) {
                        if (card == j) {
                            JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                            alive = true;
                            cardApply.applyCard(j);
                            //allCards.remove(0);
                            int randomIndex = random.nextInt(allCards.size());
                            allCards.add(randomIndex, 15);
                            allCards.remove(0);
                            break outer;
                        }
                    }
                }
            }
*/

            if (alive == false) {
                gameFlow.getPlayer(player.getPlayerId()).setAlive(false);
                allCards.remove(0);
                gameFlow.addFoldCard(15);

            }
            return;

        }

        if (theFirstCard == 16) {
            System.out.println("Player " + player.getPlayerId() + " 抽到 煤氣外洩");
            MessageFrame messageFrame = new MessageFrame(16, "Player " + player.getPlayerId() + " 抽到");
            boolean alive = false;
            boolean hasCustomizedCard=false;
            boolean hasUniversalCard=false;

            outer:for(int card : currentHandCard){
                if(card == 9){
                    hasCustomizedCard=true;
                    //break;
                }
                else if(card == 10){
                    hasCustomizedCard=true;
                    //break;
                }
                else{
                    for (int j = 0; j <= 6; j++){
                        if (card == j){
                            hasUniversalCard=true;
                            //break outer;
                        }
                    }
                }
            }

            if(hasCustomizedCard==true){
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)==9){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有風扇，逃過一劫");
                        alive = true;
                        cardApply.applyCard(9);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 16);
                        allCards.remove(0);
                        break;
                    }
                    else if(currentHandCard.get(j)==10){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有風扇，逃過一劫");
                        alive = true;
                        cardApply.applyCard(10);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 16);
                        allCards.remove(0);
                        break;
                    }
                }
            }
            else if(hasCustomizedCard==false&&hasUniversalCard==true){

                int universalCardNumber=999;
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)>=0&&currentHandCard.get(j)<=6){
                        universalCardNumber=currentHandCard.get(j);
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                        alive = true;
                        cardApply.applyCard(universalCardNumber);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 16);
                        allCards.remove(0);
                        break;
                    }
                }
            }

            /*
            outer: for (int card : currentHandCard) {
                if (card == 9) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有風扇，逃過一劫");
                    alive = true;
                    cardApply.applyCard(9);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 16);
                    allCards.remove(0);
                    break;
                } else if (card == 10) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有風扇，逃過一劫");
                    alive = true;
                    cardApply.applyCard(10);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 16);
                    allCards.remove(0);
                    break;
                } else {
                    for (int j = 0; j <= 6; j++) {
                        if (card == j) {
                            JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                            alive = true;
                            cardApply.applyCard(j);
                            //allCards.remove(0);
                            int randomIndex = random.nextInt(allCards.size());
                            allCards.add(randomIndex, 16);
                            allCards.remove(0);
                            break outer;
                        }
                    }
                }
            }
            */
            if (alive == false) {
                gameFlow.getPlayer(player.getPlayerId()).setAlive(false);
                allCards.remove(0);
                gameFlow.addFoldCard(16);
            }
            return;

        }

        if (theFirstCard == 17) {
            System.out.println("Player " + player.getPlayerId() + " 抽到 坍塌");
            MessageFrame messageFrame = new MessageFrame(17, "Player " + player.getPlayerId() + " 抽到");
            boolean alive = false;
            boolean hasCustomizedCard=false;
            boolean hasUniversalCard=false;

            outer:for(int card : currentHandCard){
                if(card == 11){
                    hasCustomizedCard=true;
                    //break;
                }
                else if(card == 12){
                    hasCustomizedCard=true;
                    //break;
                }
                else{
                    for (int j = 0; j <= 6; j++){
                        if (card == j){
                            hasUniversalCard=true;
                            //break outer;
                        }
                    }
                }
            }

            if(hasCustomizedCard==true){
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)==11){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有逃跑，逃過一劫");
                        alive = true;
                        cardApply.applyCard(11);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 17);
                        allCards.remove(0);
                        break;
                    }
                    else if(currentHandCard.get(j)==12){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有逃跑，逃過一劫");
                        alive = true;
                        cardApply.applyCard(12);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 17);
                        allCards.remove(0);
                        break;
                    }
                }
            }
            else if(hasCustomizedCard==false&&hasUniversalCard==true){

                int universalCardNumber=999;
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)>=0&&currentHandCard.get(j)<=6){
                        universalCardNumber=currentHandCard.get(j);
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                        alive = true;
                        cardApply.applyCard(universalCardNumber);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 17);
                        allCards.remove(0);
                        break;
                    }
                }
            }
            /*
            outer: for (int card : currentHandCard) {
                if (card == 11) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有逃跑，逃過一劫");
                    alive = true;
                    cardApply.applyCard(11);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 17);
                    allCards.remove(0);
                    break;
                } else if (card == 12) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有逃跑，逃過一劫");
                    alive = true;
                    cardApply.applyCard(12);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 17);
                    allCards.remove(0);
                    break;
                } else {
                    for (int j = 0; j <= 6; j++) {
                        if (card == j) {
                            JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                            alive = true;
                            cardApply.applyCard(j);
                            //allCards.remove(0);
                            int randomIndex = random.nextInt(allCards.size());
                            allCards.add(randomIndex, 17);
                            allCards.remove(0);
                            break outer;
                        }
                    }
                }
            }
            */
            if (alive == false) {
                gameFlow.getPlayer(player.getPlayerId()).setAlive(false);
                allCards.remove(0);
                gameFlow.addFoldCard(17);
            }
            return;

        }

        if (theFirstCard == 18) {
            System.out.println("Player " + player.getPlayerId() + " 抽到 爆炸");
            MessageFrame messageFrame = new MessageFrame(18, "Player " + player.getPlayerId() + " 抽到");
            boolean alive = false;
            boolean hasCustomizedCard=false;
            boolean hasUniversalCard=false;

            outer:for(int card : currentHandCard){
                if(card == 13){
                    hasCustomizedCard=true;
                    //break;
                }
                else if(card == 14){
                    hasCustomizedCard=true;
                    //break;
                }
                else{
                    for (int j = 0; j <= 6; j++){
                        if (card == j){
                            hasUniversalCard=true;
                            //break outer;
                        }
                    }
                }
            }

            if(hasCustomizedCard==true){
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)==13){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有救災，逃過一劫");
                        alive = true;
                        cardApply.applyCard(13);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 18);
                        allCards.remove(0);
                        break;
                    }
                    else if(currentHandCard.get(j)==14){
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有救災，逃過一劫");
                        alive = true;
                        cardApply.applyCard(14);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 18);
                        allCards.remove(0);
                        break;
                    }
                }
            }
            else if(hasCustomizedCard==false&&hasUniversalCard==true){

                int universalCardNumber=999;
                for(int j=0;j<currentHandCard.size();j++){
                    if(currentHandCard.get(j)>=0&&currentHandCard.get(j)<=6){
                        universalCardNumber=currentHandCard.get(j);
                        JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                        alive = true;
                        cardApply.applyCard(universalCardNumber);
                        int randomIndex = random.nextInt(allCards.size());
                        allCards.add(randomIndex, 18);
                        allCards.remove(0);
                        break;
                    }
                }
            }
            /*
            outer: for (int card : currentHandCard) {
                if (card == 13) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有救災，逃過一劫");
                    alive = true;
                    cardApply.applyCard(13);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 18);
                    allCards.remove(0);
                    break;
                } else if (card == 14) {
                    JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有救災，逃過一劫");
                    alive = true;
                    cardApply.applyCard(14);
                    //allCards.remove(0);
                    int randomIndex = random.nextInt(allCards.size());
                    allCards.add(randomIndex, 18);
                    allCards.remove(0);
                    break;
                } else {
                    for (int j = 0; j <= 6; j++) {
                        if (card == j) {
                            JOptionPane.showMessageDialog(null, "Player " + player.getPlayerId() + " 有萬能牌，逃過一劫");
                            alive = true;
                            cardApply.applyCard(j);
                            //allCards.remove(0);
                            int randomIndex = random.nextInt(allCards.size());
                            allCards.add(randomIndex, 18);
                            allCards.remove(0);
                            break outer;
                        }
                    }
                }
            }
            */
            if (alive == false) {
                gameFlow.getPlayer(player.getPlayerId()).setAlive(false);
                allCards.remove(0);
                gameFlow.addFoldCard(18);
            }

            return;
        }
        currentHandCard.add(theFirstCard);
        player.setHandCard(currentHandCard);
        allCards.remove(0);
    }

    class MessageFrame extends JFrame {
        public MessageFrame(int drawCardNumber, String message) {
            //super("message!");
            setTitle("Message!");
            setLayout(new FlowLayout());
            setBounds(500, 300, 400, 300);
            setDefaultCloseOperation(HIDE_ON_CLOSE);

            Icon bg = new ImageIcon("Card/" + drawCardNumber + ".png");
            JLabel picture = new JLabel(message);
            picture.setVerticalTextPosition(SwingConstants.BOTTOM);
            picture.setHorizontalTextPosition(SwingConstants.CENTER);
            picture.setIcon(bg);

            add(picture);

            setVisible(true);
            MessageDoNothing(2000);
            setVisible(false);
        }
    }

    private static void MessageDoNothing(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }

    }
    

}