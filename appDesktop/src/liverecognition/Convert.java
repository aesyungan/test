/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liverecognition;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Alex
 */
public class Convert {

    private static final int IMG_WIDTH = 125;
    private static final int IMG_HEIGHT = 150;

    /*
    public static BufferedImage IplImageToBufferedImage(opencv_core.IplImage src) {
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        Frame frame = grabberConverter.convert(src);
        return paintConverter.getBufferedImage(frame, 1);
    }*/
    public static BufferedImage toBufferedImage(byte[] data) {
        BufferedImage bImageFromConvert = null;
        try {


            /*  byte[] imageInByte = null;
                        BufferedImage originalImage = null;
                        originalImage = ImageIO.read(new File("E:\\img\\foto1.jpg"));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(originalImage, "jpg", baos);
                        baos.flush();
                        imageInByte = baos.toByteArray();*/
            InputStream in = new ByteArrayInputStream(data);
            bImageFromConvert = ImageIO.read(in);
        } catch (Exception e) {
        }

        return bImageFromConvert;

    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public static void saveImg(String nombre, int current, BufferedImage auxBufer) {
        try {

            File outputfile = new File("trainingImages/" + nombre + current + ".png");
            ImageIO.write(auxBufer, "png", outputfile);
            // ImageIO.write(awtImage[0], "jpg", new File("C:\\out.jpg"));

            // Files.write(path, awtImage[0]);
        } catch (IOException ex) {
            System.out.println("error ->" + ex.getMessage());
        }
    }

    public static BufferedImage scaleImage(byte[] imageData) {

        ImageIcon icon = new ImageIcon(imageData);
        // int w = icon.getIconWidth() + icon.getIconWidth();
        // int h = icon.getIconHeight() + icon.getIconHeight();
        // Image img = icon.getImage();
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        BufferedImage buffered = (BufferedImage) newimg;
        // return new ImageIcon(newimg);
        return buffered;
    }

    public static byte[] toByte(BufferedImage data) {
        byte[] imageInByte = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(data, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
        } catch (Exception e) {
            System.out.println("Error al convertir buffer image to byte array ->" + e.getMessage());
        }
        return imageInByte;
    }
}
