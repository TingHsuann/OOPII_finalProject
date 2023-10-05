import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayFrame extends JFrame {
    private card_panel panel;
    private JButton drawCard;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    private JLabel player4;
    private ArrayList<Integer[]> location = new ArrayList<>();
    private ArrayList<Integer> card = new ArrayList<>(); // use game
    private ArrayList<Integer> foldCard = new ArrayList<>();// use game
    private boolean ifChoose = false;
    private boolean ifDrag = false;
    private boolean ifCantDrag = true;
    private static final int CENTER = 1500 / 2;
    private Integer chooseCardInfor[] = { -1, -1 };
    private Integer chooseCardLoc[] = { 0, 0 };
    private Integer chooseCardLocOringinal[] = { 0, 0 };
    private static int ReleasedX = 0;
    private static int ReleasedY = 0;
    private static int DraggedX1 = 0;
    private static int DraggedY1 = 0;
    private static int DraggedX2 = 0;
    private static int DraggedY2 = 0;
    private GameFlow gameFlow;
    private DealWithCard dealwithcard;
    private Color buttonColor;
    private Threads thread;
    private JLabel goldLabel;

    public PlayFrame(Threads thread) {
        super("mine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 900);
        this.thread = thread;
        this.dealwithcard = this.thread.getDealWithCard();
        this.gameFlow = this.thread.getGameFlow();
        this.foldCard = this.thread.getGameFlow().getFoldCard();
        this.card = this.thread.getGameFlow().getPlayer(0).getHandCard();

        panel = new card_panel();
        add(panel);
        changeCard(card, location);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (thread.getComputerPhase()) {
                } else {
                    DraggedX1 = e.getX();
                    DraggedY1 = e.getY();
                    for (int i = 0; i < card.size(); i++) {
                        int last = 60;
                        if (i == card.size() - 1) {
                            last = 118;
                        }

                        if (location.get(i)[0] - 1 < DraggedX1 & DraggedX1 < location.get(i)[0] + 1 + last
                                & location.get(i)[1] - 1 < DraggedY1 & DraggedY1 < location.get(i)[1] + 1 + 180) {
                            ifChoose = true;
                            chooseCardInfor[0] = i;
                            chooseCardInfor[1] = thread.getGameFlow().getPlayer(0).getHandCard().get(i);// .png
                            chooseCardLoc[0] = location.get(i)[0];// x
                            chooseCardLoc[1] = location.get(i)[1];// y
                            chooseCardLocOringinal[0] = location.get(i)[0];
                            chooseCardLocOringinal[1] = location.get(i)[1];
                            DraggedX2 = e.getX();
                            DraggedY2 = e.getY();
                            thread.getGameFlow().getPlayer(0).getHandCard().remove(i);//
                            location.remove(i);
                            changeCard(card, location);
                            thread.getGameFlow().getPlayer(0)
                                    .setHandCard(thread.getGameFlow().getPlayer(0).getHandCard());
                            // System.out.println("remove1");
                        }

                    }
                }
                if (chooseCardInfor[1] < 15 && chooseCardInfor[1] > -1) {
                    ifChoose = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                boolean ifAdd = false;
                if (chooseCardInfor[0] != -1 && chooseCardInfor[1] != -1) {
                    ReleasedX = e.getX();
                    ReleasedY = e.getY();
                    if (ifChoose) {
                        if (ifDrag) {// choose Function card
                            card.add(chooseCardInfor[0], chooseCardInfor[1]);
                            location.add(chooseCardInfor[0], chooseCardLocOringinal);
                            ifAdd = true;
                            // System.out.println("add1");
                            if (675 - 3 < ReleasedX & ReleasedX < 675 + 3 + 140
                                    & 340 - 3 < ReleasedY & ReleasedY < 340 + 3 + 190 & ifDrag) {
                                int a = chooseCardInfor[0];
                                System.out.println(chooseCardInfor[0]);
                                // thread.getGameFlow().getPlayer(0).getHandCard().remove(a);
                                thread.getGameFlow().getPlayer(0)
                                        .setHandCard(thread.getGameFlow().getPlayer(0).getHandCard());
                                System.out.println(thread.getGameFlow().getPlayer(0).getHandCard());
                                location.remove(a);
                                // foldCard.add(chooseCardInfor[1]);

                                thread.setUseCard(chooseCardInfor[1]);
                                drawCard.setEnabled(false);
                                thread.setComputerPhase(true);
                                thread.setDrawMode(2);

                            }
                        }

                    } else if (chooseCardInfor[1] < 15 && chooseCardInfor[1] > -1 && ifCantDrag) {
                        card.add(chooseCardInfor[0], chooseCardInfor[1]);
                        location.add(chooseCardInfor[0], chooseCardLocOringinal);
                        // System.out.println("add2");
                    }
                    if (ifChoose & DraggedX1 == ReleasedX & DraggedY1 == ReleasedY & !ifAdd) {
                        card.add(chooseCardInfor[0], chooseCardInfor[1]);
                        location.add(chooseCardInfor[0], chooseCardLocOringinal);
                        // System.out.println("add3");
                    }

                    changeCard(card, location);
                    ifDrag = false;
                    ifChoose = false;
                    ifCantDrag = true;
                }
                chooseCardInfor[0] = -1;
                chooseCardInfor[1] = -1;
                repaint();

            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                if (ifChoose) {
                    // drawCard.setEnabled(true);
                    ifDrag = true;
                    changeCard(card, location);
                    DraggedX1 = DraggedX2;
                    DraggedY1 = DraggedY2;
                    DraggedX2 = e.getX();
                    DraggedY2 = e.getY();
                    chooseCardLoc[0] = chooseCardLoc[0] - (DraggedX1 - DraggedX2);
                    chooseCardLoc[1] = chooseCardLoc[1] - (DraggedY1 - DraggedY2);
                    repaint();
                }

                if (!ifChoose && chooseCardInfor[1] < 15 && chooseCardInfor[1] > -1 && ifCantDrag) {
                    ifCantDrag = false;
                    card.add(chooseCardInfor[0], chooseCardInfor[1]);
                    location.add(chooseCardInfor[0], chooseCardLocOringinal);

                }

            }
        });

    }

    public void changeCard(ArrayList<Integer> card, ArrayList<Integer[]> location) {

        location.clear();
        if (card.size() % 2 == 0) {// even
            int start = CENTER - (card.size() / 2) * 60;

            for (int i = 0; i < card.size(); i++) {
                Integer[] temp = { start + 60 * i, 651 };
                location.add(temp);
            }
        } else if (card.size() % 2 != 0) {// odd
            int start = CENTER - (card.size() / 2) * 60 - 30;
            for (int i = 0; i < card.size(); i++) {
                Integer[] temp = { start + 60 * i, 651 };
                location.add(temp);
            }
        }
        setCard(card);
    }

    public void setCard(ArrayList<Integer> card) {
        this.card = card;
    }

    class card_panel extends JPanel {
        public card_panel() {
            setLayout(null);
            ImageIcon drawCardImg = new ImageIcon("Card/drawCard.png");
            drawCard = new JButton(drawCardImg);

            ImageIcon selectDrawCard = new ImageIcon("Card/selectDrawCard.png");
            drawCard.setRolloverIcon(selectDrawCard);
            ImageIcon pressDrawCard = new ImageIcon("Card/pressDrawCard.png");
            drawCard.setPressedIcon(pressDrawCard);

            drawCard.addActionListener(new ActionListener() {
                // int count = 0;

                @Override
                public void actionPerformed(ActionEvent e) {

                    drawCard.setEnabled(false);
                    thread.setComputerPhase(true);
                    changeCard(card, location);
                    thread.setDrawMode(1);
                    repaint();

                }
            });
            add(drawCard);
            drawCard.setSize(200, 200);
            drawCard.setLocation(900, 340);
            drawCard.setBackground(Color.BLACK);
            drawCard.setBorderPainted(false);
            drawCard.setFocusPainted(false);
            drawCard.setContentAreaFilled(false);

            ImageIcon backImg = new ImageIcon("Card/backImg.png");
            JButton backButton = new JButton(backImg);
            ImageIcon selectBack = new ImageIcon("Card/backselect.png");
            backButton.setRolloverIcon(selectBack);
            backButton.setBackground(Color.BLACK);
            backButton.setBorderPainted(false);
            backButton.setFocusPainted(false);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int opt = JOptionPane.showConfirmDialog(null, "你想要離開遊戲嗎?", "waring",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opt == JOptionPane.YES_OPTION) {
                        gameFlow.setOneMoreGame(true);
                        dispose();
                        new StartFrame();
                    } else if (opt == JOptionPane.NO_OPTION) {

                    }

                }

            });
            add(backButton);
            backButton.setSize(64, 64);
            backButton.setLocation(20, 20);
            backButton.setContentAreaFilled(false);

            ImageIcon ruleImg = new ImageIcon("Card/rule.png");
            JButton ruleButton = new JButton(ruleImg);
            ruleButton.setBackground(Color.BLACK);
            ruleButton.setBorderPainted(false);
            ruleButton.setFocusPainted(false);
            ImageIcon selectRule = new ImageIcon("Card/ruleselect.png");
            ruleButton.setRolloverIcon(selectRule);
            ImageIcon rulePressImg = new ImageIcon("Card/rulePress.png");
            ruleButton.setPressedIcon(rulePressImg);
            ruleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RuleFrame ruleFrame = new RuleFrame(ruleButton);
                    ruleFrame.setBounds(330, 150, 860, 730);

                    ruleFrame.setVisible(true);
                    ruleButton.setEnabled(false);

                }

            });

            Font f = new Font(null, Font.BOLD, 30);

            add(ruleButton);
            ruleButton.setSize(100, 110);
            ruleButton.setLocation(1375, 10);
            ruleButton.setContentAreaFilled(false);

            ImageIcon goldIcon = new ImageIcon("Card/littleGold.png");
            goldLabel = new JLabel(goldIcon);
            add(goldLabel);
            goldLabel.setSize(300, 200);
            goldLabel.setLocation(5, 680);
            goldLabel.setBackground(Color.BLACK);
            goldLabel.setText("  X  " + gameFlow.getCoinCount());
            goldLabel.setForeground(Color.YELLOW);
            goldLabel.setFont(f);

            player1 = new JLabel(String.valueOf(gameFlow.getPlayer(1).getHandCard().size()), JLabel.CENTER);
            player1.setFont(f);
            add(player1);
            player1.setSize(60, 30);
            player1.setLocation(255, 200);

            player2 = new JLabel(String.valueOf(gameFlow.getPlayer(2).getHandCard().size()), JLabel.CENTER);
            player2.setFont(f);
            add(player2);
            player2.setSize(60, 30);
            player2.setLocation(565, 200);

            player3 = new JLabel(String.valueOf(gameFlow.getPlayer(3).getHandCard().size()), JLabel.CENTER);
            player3.setFont(f);
            add(player3);
            player3.setSize(60, 30);
            player3.setLocation(875, 200);

            player4 = new JLabel(String.valueOf(gameFlow.getPlayer(4).getHandCard().size()), JLabel.CENTER);
            player4.setFont(f);
            add(player4);
            player4.setSize(60, 30);
            player4.setLocation(1185, 200);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (!thread.getComputerPhase()) {
                drawCard.setEnabled(true);
            }

            card = thread.getGameFlow().getPlayer(0).getHandCard();
            if (thread.getGameFlow().getClockwise()) {
                Image bg = new ImageIcon("Card/bg1.png").getImage();
                g.drawImage(bg, 0, 0, null);
            } else {
                Image bg = new ImageIcon("Card/bg2.png").getImage();
                g.drawImage(bg, 0, 0, null);
            }
            // player
            for (int i = 0; i < 4; i++) {

                if (i + 1 == thread.getnowPlayer() && thread.getGameFlow().getPlayer(i + 1).getAlive()) {
                    Image image = new ImageIcon("Card/now" + (i + 1) + ".png").getImage();
                    g.drawImage(image, 180 + 309 * i, 40, 200, 200, null);
                } else if (thread.getGameFlow().getPlayer(i + 1).getAlive()) {
                    Image image = new ImageIcon("Card/wait" + (i + 1) + ".png").getImage();
                    g.drawImage(image, 180 + 309 * i, 40, 200, 200, null);
                } else {
                    Image image = new ImageIcon("Card/player2.png").getImage();
                    g.drawImage(image, 180 + 309 * i, 40, 200, 200, null);
                }
            }

            // foldCard
            if (foldCard.size() != 0) {
                Image image = new ImageIcon("Card/" + foldCard.get(foldCard.size() - 1) + ".png").getImage();
                g.drawImage(image, 687, 352, null);// 680,351
            }

            // handCard
            if (ifDrag) {

                for (int i = 0; i < card.size(); i++) {
                    Image image = new ImageIcon("Card/" + card.get(i) + ".png").getImage();
                    g.drawImage(image, location.get(i)[0], location.get(i)[1], null);

                }

                Image image = new ImageIcon("Card/" + chooseCardInfor[1] + ".png").getImage();
                g.drawImage(image, chooseCardLoc[0], chooseCardLoc[1], null);
            } else {
                for (int i = 0; i < card.size(); i++) {
                    Image image = new ImageIcon("Card/" + card.get(i) + ".png").getImage();
                    g.drawImage(image, location.get(i)[0], location.get(i)[1], null);

                }
            }

            player1.setText(String.valueOf(thread.getGameFlow().getPlayer(1).getHandCard().size()));
            player2.setText(String.valueOf(thread.getGameFlow().getPlayer(2).getHandCard().size()));
            player3.setText(String.valueOf(thread.getGameFlow().getPlayer(3).getHandCard().size()));
            player4.setText(String.valueOf(thread.getGameFlow().getPlayer(4).getHandCard().size()));

        }
    }

    public ArrayList<Integer> getCard() {
        return thread.getGameFlow().getPlayer(0).getHandCard();
    }

    public ArrayList<Integer[]> getlocation() {
        return this.location;
    }

    public void setlocation(ArrayList<Integer[]> location) {
        this.location = location;
    }

    private static void doNothing(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }

    }

    public void setGoldLabelTest(String s) {
        this.goldLabel.setText(s);
    }

    public GameFlow getGameFlow() {
        return this.gameFlow;
    }
}