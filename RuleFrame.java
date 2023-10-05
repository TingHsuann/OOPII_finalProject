import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;

public class RuleFrame extends JFrame {
    private int index = 0;

    public RuleFrame(JButton ruleButton) {
        super("Rule");
        setTitle("How to play");
        setLayout(new BorderLayout());
        // this.getContentPane().setBackground(Color.WHITE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ruleButton.setEnabled(true);
            }

            public void windowDeactivated(WindowEvent e) {
                dispose();
            }
        });

        int[] rule = { 0, 1, 2, 3, 4, 5 };

        Icon bg = new ImageIcon("Rule/" + rule[index] + ".png");
        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new FlowLayout());
        JLabel picture = new JLabel("");
        picture.setIcon(bg);
        picturePanel.add(picture);
        add(picturePanel);
        picturePanel.setBackground(Color.white);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        ImageIcon leftImg = new ImageIcon("Rule/left.png");
        JButton left = new JButton(leftImg);
        left.setBackground(Color.WHITE);
        Dimension dimension = new Dimension(leftImg.getIconWidth(), leftImg.getIconHeight());
        left.setPreferredSize(dimension);
        left.setMaximumSize(dimension);
        left.setMinimumSize(dimension);
        left.setBorderPainted(false);
        left.setFocusPainted(false);
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index == 0) {
                    index = 5;
                } else {
                    index--;
                }

                Icon ruleImg = new ImageIcon("Rule/" + rule[index] + ".png");
                picture.setIcon(ruleImg);
                if (index == 0) {
                    index = 5;
                }
            }
        });

        ImageIcon rightImg = new ImageIcon("Rule/right.png");
        JButton right = new JButton(rightImg);
        right.setBackground(Color.WHITE);
        right.setPreferredSize(dimension);
        right.setMaximumSize(dimension);
        right.setMinimumSize(dimension);
        right.setBorderPainted(false);
        right.setFocusPainted(false);
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index == 5) {
                    index = 0;
                } else {
                    index++;
                }

                Icon ruleImg = new ImageIcon("Rule/" + rule[index] + ".png");
                picture.setIcon(ruleImg);
                if (index > 5) {
                    index = 0;
                }
            }
        });

        JLabel space = new JLabel(
                "                                                                                                                                    ");

        buttonPanel.add(left, BorderLayout.WEST);
        buttonPanel.add(space, BorderLayout.CENTER);
        buttonPanel.add(right, BorderLayout.EAST);

        buttonPanel.setBackground(Color.white);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

}