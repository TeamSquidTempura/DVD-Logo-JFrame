package com.squidtempura;

import javax.swing.*;
import java.awt.*;


import static com.squidtempura.Main.*;

public class Canvas extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
//        g.fillOval((x-size/2), (y-size/2), size, size);
//        g.setColor(invertColor(color));
        g.setFont(Main.font.deriveFont((Main.H+Main.W)/20f));
//        g.drawString("DVD", (x-size/5), (y+size/8));
        g.drawString("j", (x-size/5), (y+size/8));
    }

}
