import java.util.ArrayList;

public class AutoStrategy 
{
    private Player user;
    private ArrayList<Integer> handcard;
    private Card card = new Card();
    private CardApply cardapply;
    private DealWithCard DEAL;
    //Player p1,p2,p3,p4;
    public AutoStrategy(Player user,CardApply cardApply)
    {
        this.cardapply=cardApply;
        this.user=user;
        this.handcard=user.getHandCard();
    }
    public void useCard(Player chosen,GameFlow GF)
    {
        int r = 0;
        r = (int)(Math.random()*2);
        DEAL = new DealWithCard(GF);
        boolean boomLocation = false;
        boolean safe = false;
        boolean allsafecard=false;
        if(r==0&&handcard.size()==0)
        {
            System.out.println("DREWCARD");
            DEAL.cardDrew(chosen,GF.getAllCard());
        }
        else if(r == 0&&handcard.size()!=0)
        {
            for(int safecard = 0;safecard < handcard.size();safecard++ )
            {
                if(handcard.get(safecard) >= 0 && handcard.get(safecard) <= 14)
                {
                    safe = true;
                    break;
                }
            }
            int count = 0;
            for(int safecard1 = 0;safecard1 < handcard.size();safecard1++ ){
                if(handcard.get(safecard1) >= 0 && handcard.get(safecard1) <= 14 && count <= handcard.size()){
                    count++;
                    if(count == handcard.size()){
                        allsafecard = true;
                    }
                }      
            }
            for(int cardNum1:handcard)
            {
                if(safe == false)
                {
                    if(cardNum1 <= 38&&cardNum1 >= 35)
                    {
                        cardapply.applyCard(cardNum1);//pass
                        break;
                    }
                    else if(cardNum1 <= 34 && cardNum1 >= 31)
                    {   
                        //指定對象回合加一
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 24 && cardNum1 >= 19)
                    {
                        //交換手牌
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 50 && cardNum1 >= 47)
                    {
                        //順序反轉
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 42 && cardNum1 >= 39 )
                    {
                        //打亂牌堆
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 30 && cardNum1 >= 25)
                    {
                        //要一張牌
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 46 && cardNum1 >= 43)
                    {
                        //查看炸彈
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 34 && cardNum1 >= 31)
                    {
                        //指定對象回合加一
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                }

                else if(safe==true&&allsafecard == true){
                    System.out.println("DREWCARD");
                    DEAL.cardDrew(chosen,GF.getAllCard());
                    break;
                }
                
                else if(safe == true&&allsafecard == false)
                {
                    for(int foldcardNum = 0;foldcardNum < GF.getFoldCard().size();foldcardNum++)
                    {
                        if(foldcardNum < 2 && GF.getFoldCard().get(foldcardNum) <= 43 && GF.getFoldCard().get(foldcardNum) >= 46)
                        {
                            boomLocation = true;
                            break;
                        }
                    }

                    if(boomLocation == true && cardNum1 <= 42 && cardNum1 >= 39 )
                    {
                        //打亂牌堆
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 30 && cardNum1 >= 25)
                    {
                        //要一張牌
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 46 && cardNum1 >= 43)
                    {
                           //查看炸彈
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 34 && cardNum1 >= 31)
                    {
                        //指定對象回合加一
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 42 && cardNum1 >= 39 )
                    {
                        //打亂牌堆
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 38&&cardNum1 >= 35)
                    {
                        cardapply.applyCard(cardNum1);//pass
                        break;
                    }
                    else if(cardNum1 <= 34 && cardNum1 >= 31)
                    {
                        //指定對象回合加一
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 24 && cardNum1 >= 19)
                    {
                        //交換手牌
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                    else if(cardNum1 <= 50 && cardNum1 >= 47)
                    {
                        //順序反轉
                        cardapply.applyCard(cardNum1);
                        break;
                    }
                   /*else{
                        System.out.println("DREWCARD");
                        DEAL.cardDrew(chosen,GF.getAllCard());
                    }*/
                }
                /*else if(safe==true&&cardNum1<19)
                {
                    System.out.println("DREWCARD");
                    DEAL.cardDrew(chosen,GF.getAllCard());
                }*/
                /*else if(handcard.size() == 0)
                {
                    System.out.println("DREWCARD");
                    DEAL.cardDrew(chosen,GF.getAllCard());
                }*/
            }
        }
        else
        {
            System.out.println("DREWCARD");
            DEAL.cardDrew(chosen,GF.getAllCard());
        }
    }
}
