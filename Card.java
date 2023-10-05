

public class Card
{
    private int ID;
  
    public String getCardName(int ID)
    {
        String name="";
        switch(ID)
        {
            case 0: case 1:case 2: case 3: case 4:case 5:case 6:
            name="LabourBurean";
            break;
            case 7:case 8:
            name="flatter";
            break;
            case 9:case 10:
            name="fan";
            break;
            case 11:case 12:
            name="escape";
            break;
            case 13:case 14:
            name="help";
            break;
            case 15:
            name="boss";
            break;
            case 16:
            name="gas";
            break;
            case 17:
            name="collapse";
            break;
            case 18:
            name="bomb";
            break;
            case 19:case 20:case 21:case 22:case 23:case 24:
            name="change";
            break;
            case 25:case 26:case 27:case 28:case 29:case 30:
            name="ask";
            break;
            case 31:case 32:case 33:case 34:
            name="again";
            break;
            case 35:case 36:case 37:case 38:
            name="skip";
            break;
            case 39:case 40:case 41:case 42:
            name="mess";
            break;
            case 43:case 44:case 45:case 46:
            name="peep";
            break;
            case 47:case 48:case 49:case 50:
            name="nap";
            break;
        }
        return name;
    }
    public String getCardintro(String cardname)
    {
        String cardintro="";
        switch(cardname)
        {
            case "change":
            cardintro="<html> 交換全部手牌<html/>";
            break;
            case "ask":
            cardintro="<html> 索取一張牌<html/>";
            break;
            case "again":
            cardintro="<html> 加一回合<html/>";
            break;
            case "skip":
            cardintro="<html> 跳過<html/>";
            break;
            case "mess":
            cardintro="<html> 洗牌<html/>";
            break;
            case "peep":
            cardintro="<html> 偷看最近一張炸彈牌位置<html/>";
            break;
            case "nap":
            cardintro="<html> 迴轉<html/>";
            break;
        }
        return cardintro;

    }
    public int getCardId()
    {
        return this.ID;
    }
} 