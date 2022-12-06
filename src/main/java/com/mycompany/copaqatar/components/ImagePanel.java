package com.mycompany.copaqatar.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {
        try {
            //new File("com/mycompany/copaqatar/assets/firedcastle.png")
            image = ImageIO.read(new File("D:/projetos/java/Copa2k22/src/main/java/com/mycompany/copaqatar/components/firedcastle.png"));
        } catch (IOException ex) {
            System.out.println("Err on read image: " + ex);
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}
