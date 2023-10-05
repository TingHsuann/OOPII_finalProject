import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StartFrame extends JFrame {
    PlayFrame playFrame;
    private static GameFlow gameFlow;
    // private static DealWithCard dealWithCard;
    private static Threads thread;
    private Thread infiniteRepaintThread;
    private infiniteRepaint infiniterepaint;
    // private CardApply cardApply = new CardApply();

    public static void main(String[] args) {
        StartFrame s = new StartFrame();
        // thread.DealWithRound();
        s.setVisible(true);

    }

    public StartFrame() {
        super("Starter");
        // this.gameFlow = gameFlow;
        // this.dealWithCard = new DealWithCard(this.gameFlow);
        setBounds(0, 0, 1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        gameFlow = new GameFlow();
        thread = new Threads(gameFlow);

        Thread start = new Thread(thread);////
        start.start();////

        playFrame = new PlayFrame(thread);
        infiniterepaint = new infiniteRepaint(this.playFrame);
        infiniteRepaintThread = new Thread(this.infiniterepaint);
        infiniteRepaintThread.start();

        ImageIcon start1Img = new ImageIcon("enter/start1.png");
        JButton startButton = new JButton(start1Img);
        ImageIcon selectRule = new ImageIcon("enter/start2.png");
        startButton.setRolloverIcon(selectRule);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                playFrame.setVisible(true);
            }
        });
        add(startButton);
        startButton.setSize(239, 180);
        startButton.setLocation(100, 600);
        startButton.setBackground(Color.BLACK);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);

        ImageIcon rule1 = new ImageIcon("enter/rule1.png");
        JButton ruleButton = new JButton(rule1);
        ImageIcon rule2 = new ImageIcon("enter/rule2.png");
        ruleButton.setRolloverIcon(rule2);
        ruleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RuleFrame ruleFrame = new RuleFrame(ruleButton);
                ruleFrame.setBounds(330, 150, 860, 730);

                ruleFrame.setVisible(true);
            }
        });
        add(ruleButton);
        ruleButton.setSize(239, 180);
        ruleButton.setLocation(380, 530);
        ruleButton.setBackground(Color.BLACK);
        ruleButton.setBorderPainted(false);
        ruleButton.setFocusPainted(false);
        ruleButton.setContentAreaFilled(false);

        ImageIcon exit1 = new ImageIcon("enter/exit1.png");
        JButton exitButton = new JButton(exit1);
        ImageIcon exit2 = new ImageIcon("enter/exit2.png");
        exitButton.setRolloverIcon(exit2);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
        exitButton.setSize(239, 180);
        exitButton.setLocation(660, 460);
        exitButton.setBackground(Color.BLACK);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);

        ImageIcon backImg = new ImageIcon("Card/startbg.png");
        JLabel startBg = new JLabel(backImg);
        add(startBg);
        startBg.setSize(1496, 873);
        startBg.setLocation(0, 0);

        setVisible(true);
    }
}