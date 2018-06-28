/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liverecognition;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author XL
 */
public class Correo {

    String usuarioCorreo;
    String contraseña;
    String rutaArchivo;
    String nombreArchivo;
    String destino;
    String asunto;
    String mensaje;

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean enviarCorreo() {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", usuarioCorreo);
            p.setProperty("mail.smtp.auth", "true");
            //sessioon
            Session s = Session.getDefaultInstance(p, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            BodyPart adjunto = new MimeBodyPart();
            if (rutaArchivo != "") {//si hay ruta de archivo
                adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaArchivo)));
                adjunto.setFileName(nombreArchivo);
                System.out.println("Si hay archivo ------------------------------------------------------");
            }

            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            if (rutaArchivo != "") {
                m.addBodyPart(adjunto);
            }
            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress(usuarioCorreo));//de 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));//a
            message.setSubject(asunto);
            message.setContent(m);

            Transport t = s.getTransport("smtp");
            t.connect(usuarioCorreo, contraseña);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            System.out.println("error al enviar mensage ->" + e.getMessage());
            return false;

        }
    }
}
