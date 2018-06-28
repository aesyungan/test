/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liverecognition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class Util {

    public static void warnigMessage(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Info!");
        dialog.setAlwaysOnTop(true); // to show top of all other application
        dialog.setVisible(true); // to visible the dialog
    }

    public static void saveImage(BufferedImage img) {

        try {
            File outputfile = new File("E:\\img\\sendImg.jpg");
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException ex) {
            System.out.println("Error al guardar Imagen ->" + ex.getMessage());
        }
    }

    public static String saveImageTemp(BufferedImage img) {
        String fileName = null;
        try {
            fileName = "sendImg.jpg";
            File outputfile = new File(fileName);
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException ex) {
            System.out.println("Error al guardar Imagen ->" + ex.getMessage());
        }
        return fileName;
    }
}
