package com.squidtempura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
        frame = new JFrame("dvd");
        display = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        W = display.getWidth()/4;
        x = W/2;
        H = display.getHeight()/2;
        y = H/2;
        size = (Main.H+Main.W)/20;
        speed = (float) W/5000*size;
        angle = (float) (Math.random()*360);
        color = Color.WHITE;
        frame.setSize(W, H);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisible"));
        Canvas panel = new Canvas();
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });



        new Timer(16, e -> {
            W = frame.getWidth();
            H = frame.getHeight();
            size = (Main.H+Main.W)/20;
            x += (int)(speed * Math.sin(angle*Math.PI/180));
            y += (int)(speed * Math.cos(angle*Math.PI/180));

            if (x-size/4 < 0) {
                x = size/4;
                angle = 360 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155+100), (int) (Math.random()*155+100));
            }

            if (x + size*2 > W) {
                x = W - size*2;
                angle = 360 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155+100), (int) (Math.random()*155+100));
            }

            if (y - size*3/4 < 0) {
                y = size*3/4;
                angle = 180 - angle;
                color = new Color((int) (Math.random()*155+100), (int) (Math.random()*155+100), (int) (Math.random()*155+100));
            }

            if (y + size/4 > H) {
                y = H - size/4;
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
