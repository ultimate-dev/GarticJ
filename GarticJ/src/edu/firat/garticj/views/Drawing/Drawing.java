package edu.firat.garticj.views.Drawing;

import edu.firat.garticj.views.canvas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing extends JFrame {

    /**
     * Container
     */
    public Container container;
    /**
     * Areas
     */
    public DrawArea drawArea;
    public Players playersArea;
    public Chat chatArea;
    public Tools toolsArea;
    public Bar barArea;

    /**
     * Elements
     */
    public JButton clearBtn, applyBtn, eraserBtn;
    public JTextField colorInput, sizeInput;


    /**
     * Listeners
     */
    public ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == applyBtn) {
                drawArea.setLineColor(colorInput.getText());
                float size = Float.parseFloat(sizeInput.getText());
                drawArea.setLineSize(size, 1);
            } else if (e.getSource() == eraserBtn) {
                drawArea.setLineColor("#ffffff");
            }
        }
    };

    /**
     * Constructor
     */
    public Drawing() {
        container = getContentPane();
        container.setLayout(new BorderLayout());

        drawArea = DrawArea.getInstance();
        playersArea = Players.getInstance();
        chatArea = new Chat();
        toolsArea = new Tools();
        barArea = new Bar();

        JPanel controls = new JPanel();

        // Clear Button
        clearBtn = new JButton("Temizle");
        clearBtn.addActionListener(actionListener);
        controls.add(clearBtn);
        // Size Button
        sizeInput = new JTextField("04");
        sizeInput.addActionListener(actionListener);
        controls.add(sizeInput);
        // Color Input
        colorInput = new JTextField("#000000");
        colorInput.addActionListener(actionListener);
        controls.add(colorInput);
        // Apply Button
        applyBtn = new JButton("Uygula");
        applyBtn.addActionListener(actionListener);
        controls.add(applyBtn);
        // Eraser Button
        eraserBtn = new JButton("Silgi");
        eraserBtn.addActionListener(actionListener);
        controls.add(eraserBtn);


        // container.add(controls, BorderLayout.SOUTH);


        JPanel centerArea = new JPanel();
        centerArea.setLayout(new BorderLayout());
        centerArea.add(drawArea, BorderLayout.CENTER);
        centerArea.add(chatArea, BorderLayout.SOUTH);
        centerArea.add(barArea, BorderLayout.NORTH);
        container.add(centerArea, BorderLayout.CENTER);
        container.add(playersArea, BorderLayout.LINE_START);
        container.add(toolsArea, BorderLayout.LINE_END);


        setTitle("GarticJ");
        setResizable(false);
        setSize(1280, 720);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int centerX = dim.width / 2 - this.getSize().width / 2;
        final int centerY = dim.height / 2 - this.getSize().height / 2;
        setLocation(centerX, centerY);
        toFront();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }


}
