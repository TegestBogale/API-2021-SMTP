import ch.heigvd.api.mailrobot.config.ConfigurationManager;
import ch.heigvd.api.mailrobot.model.mail.Message;
import ch.heigvd.api.mailrobot.model.prank.Prank;
import ch.heigvd.api.mailrobot.model.prank.PrankGenerator;
import ch.heigvd.api.mailrobot.smtp.SmtpClient;
import java.io.IOException;
import java.util.List;

public class MailRobot {
    public static void main(String[] args)throws IOException {
        ConfigurationManager configurationManager = new ConfigurationManager();
        PrankGenerator prankGenerator = new PrankGenerator(configurationManager);
        List<Prank> pranks = prankGenerator.generatePranks();

        SmtpClient smtpClient = new SmtpClient(configurationManager.getSmtpServerAddress(),configurationManager.getSmtpServerPort());

        for(Prank p : pranks){
            Message message = new Message();
            String[] to = new String[p.getVictimRecipients().size()];
            String[] Cc = new String[p.getWitnessRecipients().size()];
           // String[] bccc = new String[p.getWitnessRecipients().size()];


            for(int i = 0; i < p.getWitnessRecipients().size(); ++i){
                to[i] = String.valueOf(p.getWitnessRecipients().get(i));
            }
            for(int i = 0; i < p.getVictimRecipients().size(); ++i){
                to[i] = String.valueOf(p.getVictimRecipients().get(i));
            }

            message.setFrom(p.getVictimSender().getAddress());
            message.setTo(to);
            message.setCc(Cc);
            message.setSubject(p.getMessage());
            message.setBody(p.getMessage());

            smtpClient.sendMessage(p.generateMessage());
        }
    }


}
