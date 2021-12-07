package ch.heigvd.api.mailrobot.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public class Group {

    private final List<Person> members = new ArrayList<>();

    public void addMemeber(Person person){
        members.add(person);
    }

    public List<Person> getMembers(){
        return new ArrayList<>(members);
    }
}
