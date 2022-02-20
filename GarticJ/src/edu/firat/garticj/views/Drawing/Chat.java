package edu.firat.garticj.views.Drawing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Chat extends JPanel {
    public Chat() {
        setBackground(Color.decode("#7859d1"));
        setPreferredSize(new Dimension(getSize().width, 70));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(15, 0, 15, 0));
        Input input = new Input();
        Submit submit = new Submit();


        add(input);
        add(submit);


    }


    class Input extends JTextField {
        public Input() {
            setBackground(Color.decode("#937ada"));
            setOpaque(true);
            setFont(new Font("Serif", Font.BOLD, 14));
            setForeground(Color.decode("#ffffff"));
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    class Submit extends JButton {
        public Submit() {
            setText("Send");
            setBorder(null);
            setBackground(Color.decode("#ffffff"));
            setOpaque(true);
            setBorder(new EmptyBorder(15, 15, 15, 15));
            setPreferredSize(new Dimension(200, 40));
            setFont(new Font("Serif", Font.BOLD, 14));
            setForeground(Color.decode("#7859d1"));
            setHorizontalAlignment(SwingConstants.CENTER);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }


}
