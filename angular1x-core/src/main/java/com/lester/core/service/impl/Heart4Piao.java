package com.lester.core.service.impl;

import javax.swing.*;
import java.awt.*;

public class Heart4Piao extends JFrame {

    public Heart4Piao() {
        super("Heart4Piao");
        this.setBackground(Color.BLACK);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2);
        this.setSize(600, 600);

        this.setVisible(true);
        this.setLayout(getLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void paint(Graphics g) {
        double x, y, r;
        Image img = this.createImage(600, 600);
        Graphics pic = img.getGraphics();
        try {
            for (int i = 179; i > -1; i--) {
                for (int j = 179; j > -1; j--) {
                    r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j)) * 18;
                    x = (r * Math.cos(Math.PI / 45 * j) * Math.sin(Math.PI / 45 * i) + 600 / 2) * 1;
                    y = (-r * Math.sin(Math.PI / 45 * j) + 500 / 4) * 1.01;
                    pic.setColor(Color.PINK);
                    pic.fillOval((int) x, (int) y, 2, 2);
                    Thread.sleep(1);
                }
                g.drawImage(img, 0, 0, this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Heart4Piao();
    }
}
