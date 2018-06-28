/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liverecognition;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 *
 * @author Alex
 */
public  class SendMail {

    public static int facesCount = 0;
    public static boolean sendEmail = false;
    public static boolean activeAlarma = false;

    public static String emailDestino = "yungan@hotmail.es";

    public static void sendEmail() {
        if (!sendEmail) {
            Thread thread = new Thread() {
                public void run() {

                    try {
                        sendEmail = true;
                        Date date = new Date();
                        Correo c = new Correo();
                        c.setUsuarioCorreo("app.encuesta.soporte.tecnico@gmail.com");
                        c.setContraseña("pzrzbvhppocbpiat");
                        c.setAsunto("Accesso no autorizado");
                        c.setMensaje("el app Video Vigilancia detecto un acceso no autorizadoa  : " + date.toString() + "");
                        c.setDestino(emailDestino);
                        c.setRutaArchivo("");//ruta
                        if (c.enviarCorreo()) {
                            facesCount = 0;
                            System.out.println("Correo enviado correctamente ");
                            Util.warnigMessage("Se envio una Alerta a \n " + emailDestino);
                            Thread.sleep(60000);

                            sendEmail = false;
                        } else {
                            System.out.println("Error al envia el correo");
                        }

                    } catch (Exception e) {
                        System.out.println("Error al envia el correo ->" + e.getMessage());
                    }

                }
            };
            thread.start();

        } else {
            System.out.println("Ya se envio un correo ");
        }
    }

    public static void sendEmail(final BufferedImage img) {
        if (!sendEmail) {
            Thread thread = new Thread() {
                public void run() {

                    try {

                        sendEmail = true;
                        Date date = new Date();
                        Correo c = new Correo();
                        c.setRutaArchivo(Util.saveImageTemp(img));
                        c.setNombreArchivo("UsuarioNoAutorizado.jpg");
                        c.setUsuarioCorreo("app.encuesta.soporte.tecnico@gmail.com");
                        c.setContraseña("pzrzbvhppocbpiat");
                        c.setAsunto("Accesso no autorizado");
                        c.setMensaje("el app Video Vigilancia detecto un acceso no autorizadoa  : " + date.toString() + "");
                        c.setDestino(emailDestino);
                        //c.setRutaArchivo("");//ruta
                        if (c.enviarCorreo()) {
                            //sendEmailSuccess = true;
                            System.out.println("Correo enviado correctamente ");
                            Util.warnigMessage("Se envio una Alerta a \n " + emailDestino);
                            Thread.sleep(60000);
                            sendEmail = false;
                            //sendEmailSuccess = false;
                        } else {
                            System.out.println("Error al envia el correo");
                        }

                    } catch (Exception e) {
                        System.out.println("Error al envia el correo ->" + e.getMessage());
                    }

                }
            };
            thread.start();

        } else {
            System.out.println("Ya se envio un correo ");
        }
    }
}
