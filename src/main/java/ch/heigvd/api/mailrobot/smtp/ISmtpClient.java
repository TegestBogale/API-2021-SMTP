package ch.heigvd.api.mailrobot.smtp;

import ch.heigvd.api.mailrobot.model.mail.Message;

import java.io.IOException;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public interface ISmtpClient {
    public void sendMessage(Message message)throws IOException;
}
