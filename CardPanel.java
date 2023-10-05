
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CardPanel extends JPanel {
    private Image bgImg = new ImageIcon("Card/mycardbg.png").getImage();
    private ArrayList<JLabel> foldCard;
    private ArrayList<Integer> handCardNum;
    private ArrayList<JLabel> handCard;
    public CardPanel(ArrayList<JLabel> foldCard, ArrayList<Integer> handCardNum, ArrayList<JLabel> handCard) {
        /*
        for(int i=0;i<handCardNum.size();i++)
        {
            System.out.print(handCardNum.get(i)+",(cardpanel)");
        }
        System.out.println();
        */
        this.foldCard=foldCard;
        this.handCardNum=handCardNum;
        this.handCard=handCard;

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < gethandCardNum().size(); i++) {
            int count = i;
            String temp = String.valueOf(gethandCardNum().get(i));

            this.handCard.add(count, new JLabel(new ImageIcon("Card/" + temp + ".png")));
            add(this.handCard.get(count));
            this.handCard.get(count).setBounds(92 * (i), 200, 90, 160);
            this.handCard.get(count).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    getfoldCard().add((JLabel) e.getSource());
                    if (getfoldCard().size() != 1) {
                        getfoldCard().get(getfoldCard().size() - 2).setVisible(false);
                    }
                    getfoldCard().get(getfoldCard().size()-1).setBounds(500, 20, 90, 160);
                    
                }
            });
        }

        
    }

    public void paintCard()
    {
        for (int i = gethandCardNum().size()-1; i < gethandCardNum().size(); i++) {
            int count = i;
            String temp = String.valueOf(gethandCardNum().get(i));

            this.handCard.add(count, new JLabel(new ImageIcon("Card/" + temp + ".png")));
            add(this.handCard.get(count));
            this.handCard.get(count).setBounds(92 * (i), 200, 90, 160);
            this.handCard.get(count).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    getfoldCard().add((JLabel) e.getSource());
                    if (getfoldCard().size() != 1) {
                        getfoldCard().get(getfoldCard().size() - 2).setVisible(false);
                    }
                    getfoldCard().get(getfoldCard().size()-1).setBounds(500, 20, 90, 160);
                    
                }
            });
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bgImg, 0, 0, 1000, 400, null);
    }

    public void setHandCardNum(ArrayList<Integer> handCardNum)
    {
        this.handCardNum=handCardNum;
    }
    public ArrayList<JLabel> getfoldCard()
    {
        return this.foldCard;
    }
    public ArrayList<JLabel> gethandCard()
    {
        return this.handCard;
    }
    public ArrayList<Integer> gethandCardNum()
    {
        return this.handCardNum;
    }
}