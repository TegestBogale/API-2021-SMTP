package ch.heigvd.api.mailrobot.model.prank;

import ch.heigvd.api.mailrobot.model.mail.Message;
import ch.heigvd.api.mailrobot.model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public class Prank {

    private String message;
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public Person getVictimSender() {
        return victimSender;
    }

    public void addVictimRecipients(List<Person> victims) {
        for (Person p : victims) {
            victimRecipients.add(p);
        }
    }

    public List<Person> getVictimRecipients() {
        return new ArrayList<>(victimRecipients);
    }
    public void addWitnessRecipients(List<Person> victims) {
        for (Person p : victims) {
            witnessRecipients.add(p);
        }
    }

    public List<Person> getWitnessRecipients() {
        return new ArrayList<>(witnessRecipients);
    }
    public Message generateMessage() {
        Message msg = new Message();
        msg.setBody(this.message + "\r\n" + victimSender.getFirstName());

        String[] to = victimRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(to);

        String[] cc = witnessRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(cc);

        msg.setFrom(victimSender.getAddress());
        return msg;
    }

}
