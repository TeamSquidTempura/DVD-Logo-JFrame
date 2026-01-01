package com.squidtempura;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static JFrame frame;
    public static java.awt.DisplayMode display;
    public static int W, H;
    public static int size;
    public static int x;
    public static int y;
    public static float speed;
    public static float angle;
    public static Color color;
    public static Font font;

    public static void main(String[] args) throws IOException, FontFormatException {
        font = Font.createFont(
                Font.TRUETYPE_FONT,
                Main.class.getResourceAsStream("/ti_logoso_tfb/TI logoso TFB.ttf")
        );
        frame = new JFrame("test");
        display = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        W = display.getWidth()/4;
        x = W/2;
        H = display.getHeight()/2;
        y = H/2;
        size = (Main.H+Main.W)/20;
        speed = (float) W /100;
        angle = (float) (Math.random()*360);
        color = Color.WHITE;
        frame.setSize(W, H);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas panel = new Canvas();
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        frame.setVisible(true);

        new Timer(16, e -> {
            W = frame.getWidth();
            H = frame.getHeight();
            size = (Main.H+Main.W)/20;
            x += (int)(speed * Math.sin(angle*Math.PI/180));
            y += (int)(speed * Math.cos(angle*Math.PI/180));

            if (x-size/2 < 0) {
                x = size/2;
                angle = 360 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155+100), (int) (Math.random()*155+100));
            }

            if (x + size*2 > W) {
                x = W - size*2;
                angle = 360 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155+100), (int) (Math.random()*155+100));
            }

            if (y - size < 0) {
                y = size;
                angle = 180 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155+100), (int) (Math.random()*155+100));
            }

            if (y + size > H) {
                y = H - size;
                angle = 180 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155), (int) (Math.random()*155));
            }
            //angle reset
            angle = (angle + 360) % 360;

            panel.repaint();
        }).start();
    }
    public static Color invertColor(Color c) {
        int r = 255 - c.getRed();
        int g = 255 - c.getGreen();
        int b = 255 - c.getBlue();
        return new Color(r, g, b);
    }
}
